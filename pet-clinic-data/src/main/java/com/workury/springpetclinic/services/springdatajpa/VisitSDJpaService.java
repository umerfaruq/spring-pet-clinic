package com.workury.springpetclinic.services.springdatajpa;

import com.workury.springpetclinic.model.Visit;
import com.workury.springpetclinic.repositories.VisitRepository;
import com.workury.springpetclinic.services.VisitService;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public void delete(Visit object) {
        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }

    @Override
    public Set<Visit> findAll() {
        return StreamSupport
            .stream(visitRepository.findAll().spliterator(), false)
            .collect(Collectors.toSet());
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }
}
