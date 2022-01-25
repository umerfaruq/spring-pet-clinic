package com.workury.springpetclinic.services;

import com.workury.springpetclinic.model.Owner;
import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
