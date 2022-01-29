package com.workury.springpetclinic.controllers;

import com.workury.springpetclinic.model.Visit;
import com.workury.springpetclinic.services.PetService;
import com.workury.springpetclinic.services.VisitService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable String petId, Model model) {
        var pet = petService.findById(Long.valueOf(petId));
        model.addAttribute("pet", pet);
        var visit = Visit.builder().pet(pet).build();
        pet.getVisits().add(visit);
        return visit;
    }

    @GetMapping({ "/new", "/new.html" })
    public String initNewVisitForm(@PathVariable String petId, Model model) {
        model.addAttribute("isNew", true);
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping({ "/new", "/new.html" })
    public String processNewVisitForm(@Valid Visit visit, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isNew", true);
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + visit.getPet().getOwner().getId();
        }
    }
}
