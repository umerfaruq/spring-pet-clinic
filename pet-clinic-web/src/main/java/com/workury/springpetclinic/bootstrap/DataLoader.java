package com.workury.springpetclinic.bootstrap;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.model.PetType;
import com.workury.springpetclinic.model.Vet;
import com.workury.springpetclinic.services.OwnerService;
import com.workury.springpetclinic.services.PetTypeService;
import com.workury.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(
        OwnerService ownerService,
        VetService vetService,
        PetTypeService petTypeService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data...");
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner = new Owner();
        owner.setFirstName("Michael");
        owner.setLastName("Weston");

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        ownerService.save(owner2);

        Vet vet = new Vet();
        vet.setFirstName("Sam");
        vet.setLastName("Axe");

        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded owners...");
    }
}
