package com.workury.springpetclinic.controllers;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.model.Pet;
import com.workury.springpetclinic.model.PetType;
import com.workury.springpetclinic.services.OwnerService;
import com.workury.springpetclinic.services.PetService;
import com.workury.springpetclinic.services.PetTypeService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(
        PetService petService,
        OwnerService ownerService,
        PetTypeService petTypeService
    ) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable String ownerId) {
        return this.ownerService.findById(Long.valueOf(ownerId));
    }

    @InitBinder("owner")
    public void initDataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({ "/pets/new", "/pets/new.html" })
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        model.addAttribute("pet", pet);
        model.addAttribute("isNew", true);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping({ "/pets/new", "/pets/new.html" })
    public String processCreationForm(
        Owner owner,
        @Valid Pet pet,
        BindingResult result,
        Model model
    ) {
        if (StringUtils.hasLength(pet.getName()) && owner.getPet(pet.getName()) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable String petId, Model model) {
        model.addAttribute("pet", this.petService.findById(Long.valueOf(petId)));
        model.addAttribute("isNew", false);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(
        @Valid Pet pet,
        BindingResult result,
        Owner owner,
        Model model
    ) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
