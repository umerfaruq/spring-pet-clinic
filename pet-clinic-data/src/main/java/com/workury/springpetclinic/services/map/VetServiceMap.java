package com.workury.springpetclinic.services.map;

import com.workury.springpetclinic.model.Specialty;
import com.workury.springpetclinic.model.Vet;
import com.workury.springpetclinic.services.SpecialtyService;
import com.workury.springpetclinic.services.VetService;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ "default", "map" })
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            if (!object.getSpecialties().isEmpty()) {
                object
                    .getSpecialties()
                    .forEach(specialty -> {
                        if (specialty.getId() == null) {
                            Specialty savedSpecialty = specialtyService.save(specialty);
                            specialty.setId(savedSpecialty.getId());
                        }
                    });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
