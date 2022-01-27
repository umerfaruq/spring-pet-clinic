package com.workury.springpetclinic.controllers;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.services.OwnerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM =
        "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    // @RequestMapping({ "", "/", "/index", "/index.html" })
    // public String listOwners(Model model) {
    //     model.addAttribute("owners", ownerService.findAll());
    //     return "owners/index";
    // }

    @GetMapping({ "/find", "/find.html" })
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({ "", "/" })
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("listOwners", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        var mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById((long) ownerId));
        return mav;
    }

    @GetMapping({ "/new", "/new.html" })
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        model.addAttribute("isNew", Boolean.TRUE);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({ "/new", "/new.html" })
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            var savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping({ "/{ownerId}/edit", "/{ownerId}/edit.html" })
    public String initUpdateForm(@PathVariable String ownerId, Model model) {
        var owner = ownerService.findById(Long.valueOf(ownerId));
        model.addAttribute(owner);
        model.addAttribute("isNew", Boolean.FALSE);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({ "/{ownerId}/edit", "/{ownerId}/edit.html" })
    public String processUpdateForm(
        @Valid Owner owner,
        BindingResult result,
        @PathVariable String ownerId
    ) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(Long.valueOf(ownerId));
            var savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
