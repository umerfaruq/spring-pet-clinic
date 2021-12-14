package com.workury.springpetclinic.services.map;

import com.workury.springpetclinic.model.Specialty;
import com.workury.springpetclinic.services.SpecialtyService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyServiceMap
    extends AbstractMapService<Specialty, Long>
    implements SpecialtyService {

    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object) {
        return super.save(object);
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
