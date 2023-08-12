package com.example.laurasaenzfinalexam.web;

import com.example.laurasaenzfinalexam.entities.category;
import com.example.laurasaenzfinalexam.entities.items;
import com.example.laurasaenzfinalexam.entities.sales;
import com.example.laurasaenzfinalexam.repositories.categoryRepository;
import com.example.laurasaenzfinalexam.repositories.itemsRepository;
import com.example.laurasaenzfinalexam.repositories.salesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class categoryControllerTest {

    category cat;
    items item;
    sales sale;

    private MockMvc mockMvc;

    @Mock
    categoryRepository catRepository;

    @Mock
    itemsRepository itemRepository;

    @Mock
    salesRepository salesRepository;


    @Mock
    View mockView;
    @Test
    void showHome() {
    }

    @InjectMocks
    categoryController catController;

    @BeforeEach
    void setup () throws ParseException {

        item = new items();
        item.setCatcode("105");
        item.setPrice(14.5);
        item.setIdesc("test");
        item.setIcode("jtest");

        MockitoAnnotations.openMocks(this);

        mockMvc= MockMvcBuilders.standaloneSetup(catController).setSingleView(mockView).build();

    }
    @Test
    void home() throws Exception{

        List<items> itemList = new ArrayList<>();
        itemList.add(item);


        when(itemRepository.findAll()).thenReturn(itemList);
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("itemList",itemList ))
                .andExpect(view().name("home"))
                .andExpect(model().attribute("itemList", hasSize(1)));
        verify(itemRepository, times(1)).findAll();
        verifyNoMoreInteractions(itemRepository);


    }
}