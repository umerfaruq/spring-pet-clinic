package com.workury.springpetclinic.repositories;

import com.workury.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {}
