package com.workury.springpetclinic.services.springdatajpa;

import com.workury.springpetclinic.model.Pet;
import com.workury.springpetclinic.repositories.PetRepository;
import com.workury.springpetclinic.services.PetService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Set<Pet> findAll() {
        return StreamSupport
            .stream(petRepository.findAll().spliterator(), false)
            .collect(Collectors.toSet());
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }
}
