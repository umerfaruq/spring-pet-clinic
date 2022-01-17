package com.workury.springpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.workury.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;

    private final Long OWNER_ID = 1L;
    private final String OWNER_NAME = "Umer";
    private final String OWNER_LAST_NAME = "Farooq";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(
            Owner.builder().id(OWNER_ID).firstName(OWNER_NAME).lastName(OWNER_LAST_NAME).build()
        );
    }

    @Test
    void testDelete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void testDeleteById() {
        ownerServiceMap.deleteById(OWNER_ID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void testFindAll() {
        var owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void testFindById() {
        var owner = ownerServiceMap.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void testFindByLastName() {
        var owner = ownerServiceMap.findByLastName(OWNER_LAST_NAME);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void testFindByLastNameNotFound() {
        var owner = ownerServiceMap.findByLastName("foo");
        assertNull(owner);
    }

    @Test
    void testSaveExistingId() {
        var id = 2L;
        var owner = Owner.builder().id(id).firstName("Ali").lastName("Farooq").build();
        ownerServiceMap.save(owner);
        assertEquals(id, owner.getId());
    }

    @Test
    void testSaveNoId() {
        var owner = Owner.builder().firstName("Ali").lastName("Farooq").build();
        ownerServiceMap.save(owner);
        assertNotNull(owner);
        assertNotNull(owner.getId());
        assertEquals(2L, owner.getId());
    }
}
