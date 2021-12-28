package com.workury.springpetclinic.repositories;

import com.workury.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {}
