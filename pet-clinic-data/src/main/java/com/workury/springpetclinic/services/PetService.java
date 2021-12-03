package com.workury.springpetclinic.services;

import com.workury.springpetclinic.model.Pet;
import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
