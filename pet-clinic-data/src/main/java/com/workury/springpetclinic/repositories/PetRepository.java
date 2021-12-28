package com.workury.springpetclinic.repositories;

import com.workury.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {}
