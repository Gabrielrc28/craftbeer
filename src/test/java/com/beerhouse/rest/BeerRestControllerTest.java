package com.beerhouse.rest;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.refEq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BeerRestController.class)
public class BeerRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    private static List<Beer> beers = new ArrayList<>();
    private static String beerJson;

    @BeforeAll
    public static void initBeersTest() throws JsonProcessingException {beers.add(Beer.builder().name("FULLER'S ESB")
            .price(26).alcoholContent("6%")
            .category("Bitter").id(1).build());

        ObjectMapper mapper = new ObjectMapper();
        beerJson = mapper.writeValueAsString(beers.get(0));
    }

    @Test
    public void getAllBeersTest() throws Exception {
        Mockito.when(this.beerService.getAllBeers()).thenReturn(beers);

        this.mockMvc.perform(get("/beers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value("1"))
                .andExpect(jsonPath("$.[0].name").value("FULLER'S ESB"))
                .andExpect(jsonPath("$.[0].alcoholContent").value("6%"))
                .andExpect(jsonPath("$.[0].category").value("Bitter"));
    }

    @Test
    public void getAllBeersWrongRequestTest() throws Exception {
        Mockito.when(this.beerService.getAllBeers()).thenReturn(beers);

        this.mockMvc.perform(delete("/beers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void saveBeerTest() throws Exception{
        this.mockMvc.perform(post("/beers")
                .content(beerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(beerService, Mockito.times(1)).insertBeer(refEq(beers.get(0)));
    }

    @Test
    public void getBeerByIdTest() throws Exception{
        Mockito.when(this.beerService.getBeerById(1)).thenReturn(beers.get(0));

        this.mockMvc.perform(get("/beers/").accept(MediaType.APPLICATION_JSON).param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("FULLER'S ESB"))
                .andExpect(jsonPath("$.alcoholContent").value("6%"))
                .andExpect(jsonPath("$.category").value("Bitter"));
    }

    @Test
    public void putBeerByIdTest() throws Exception{
        this.mockMvc.perform(patch("/beers/").accept(MediaType.APPLICATION_JSON).param("id", "1")
                .content(beerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(beerService, Mockito.times(1)).updateBeerById(refEq(beers.get(0)),refEq(1));
    }

    @Test
    public void deleteBeerByIdTest() throws Exception{
        this.mockMvc.perform(delete("/beers/").accept(MediaType.APPLICATION_JSON).param("id", "1"))
                .andExpect(status().isNoContent());

        Mockito.verify(beerService, Mockito.times(1)).deleteBeer(refEq(1));
    }
}
