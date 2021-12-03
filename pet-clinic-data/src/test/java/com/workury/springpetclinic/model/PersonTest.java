package com.workury.springpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setFirstName("John");
    }

    @Test
    void testGetFirstName() {
        assertEquals("John", person.getFirstName());
    }
}
