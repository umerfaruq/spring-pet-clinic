package com.workury.springpetclinic.formatters;

import com.workury.springpetclinic.model.PetType;
import com.workury.springpetclinic.services.PetTypeService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType object, Locale locale) {
        return object.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        var petTypes = petTypeService.findAll();
        for (var petType : petTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException("PetType not found: " + text, 0);
    }
}
