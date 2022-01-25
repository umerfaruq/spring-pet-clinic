package com.workury.springpetclinic.services.springdatajpa;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.repositories.OwnerRepository;
import com.workury.springpetclinic.repositories.PetRepository;
import com.workury.springpetclinic.repositories.PetTypeRepository;
import com.workury.springpetclinic.services.OwnerService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(
        OwnerRepository ownerRepository,
        PetRepository petRepository,
        PetTypeRepository petTypeRepository
    ) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return StreamSupport
            .stream(ownerRepository.findAll().spliterator(), false)
            .collect(Collectors.toSet());
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }
}
