package com.workury.springpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.repositories.OwnerRepository;
import com.workury.springpetclinic.repositories.PetRepository;
import com.workury.springpetclinic.repositories.PetTypeRepository;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

    private static final String OWNER_LAST_NAME = "Smith";

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerSDJpaService service;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(OWNER_LAST_NAME).build();
    }

    @Test
    void testDelete() {
        service.delete(returnOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void testFindAll() {
        var returnOwnersSet = new HashSet<Owner>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn((Iterable<Owner>) returnOwnersSet);

        var owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());

        verify(ownerRepository).findAll();
    }

    @Test
    void testFindById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        var owner = service.findById(1L);
        assertNotNull(owner);
        assertEquals(returnOwner.getId(), owner.getId());

        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void testFindByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        var owner = service.findById(1L);
        assertNull(owner);
    }

    @Test
    void testFindByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);

        var smith = service.findByLastName(OWNER_LAST_NAME);
        assertEquals(OWNER_LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(anyString());
    }

    @Test
    void testSave() {
        var ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        var savedOwner = service.save(ownerToSave);
        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }
}
