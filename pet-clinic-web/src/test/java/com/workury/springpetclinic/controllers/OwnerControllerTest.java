package com.workury.springpetclinic.controllers;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.services.OwnerService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    private OwnerController controller;

    private Set<Owner> owners;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testFindOwners() throws Exception {
        mockMvc
            .perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(view().name("notImplemented"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void testListOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc
            .perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/index"))
            .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void testShowOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc
            .perform(get("/owners/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownerDetails"))
            .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }
}
