package com.workury.springpetclinic.services.springdatajpa;

import com.workury.springpetclinic.model.Vet;
import com.workury.springpetclinic.repositories.VetRepository;
import com.workury.springpetclinic.services.VetService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }

    @Override
    public Set<Vet> findAll() {
        return StreamSupport
            .stream(vetRepository.findAll().spliterator(), false)
            .collect(Collectors.toSet());
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }
}
