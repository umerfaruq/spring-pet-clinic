package com.workury.springpetclinic.services;

import com.workury.springpetclinic.model.Vet;
import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
