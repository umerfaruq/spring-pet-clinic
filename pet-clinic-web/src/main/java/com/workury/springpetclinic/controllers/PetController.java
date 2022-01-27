package com.workury.springpetclinic.controllers;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.model.PetType;
import com.workury.springpetclinic.services.OwnerService;
import com.workury.springpetclinic.services.PetService;
import com.workury.springpetclinic.services.PetTypeService;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
}
