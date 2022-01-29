package com.workury.springpetclinic.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.workury.springpetclinic.model.Owner;
import com.workury.springpetclinic.model.Pet;
import com.workury.springpetclinic.model.Visit;
import com.workury.springpetclinic.services.PetService;
import com.workury.springpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void testInitNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());

        mockMvc
            .perform(get("/owners/1/pets/1/visits/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visit"))
            .andExpect(view().name("pets/createOrUpdateVisitForm"));

        verify(petService, times(1)).findById(anyLong());
    }

    @Test
    void testProcessNewVisitForm() throws Exception {
        when(visitService.save(any())).thenReturn(Visit.builder().id(1L).build());
        when(petService.findById(anyLong()))
            .thenReturn(Pet.builder().id(1L).owner(Owner.builder().id(1L).build()).build());

        mockMvc
            .perform(post("/owners/1/pets/1/visits/new"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/1"));

        verify(visitService, times(1)).save(any());
    }
}
