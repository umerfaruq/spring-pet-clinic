package com.workury.springpetclinic.services.springdatajpa;

import com.workury.springpetclinic.model.Specialty;
import com.workury.springpetclinic.repositories.SpecialtyRepository;
import com.workury.springpetclinic.services.SpecialtyService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void delete(Specialty object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Set<Specialty> findAll() {
        return StreamSupport
            .stream(specialtyRepository.findAll().spliterator(), false)
            .collect(Collectors.toSet());
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specialtyRepository.save(object);
    }
}
