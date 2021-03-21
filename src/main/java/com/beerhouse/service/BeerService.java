package com.beerhouse.service;

import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class BeerService {

    BeerRepository beerRepository;

    public List<Beer> getAllBeers(){return beerRepository.findAll();}

    public void updateBeerById(Beer beer, Integer id) throws Exception {
        if(beerRepository.existsById(id)){beerRepository.save(beer);}

        else{throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
    }

    public Beer getBeerById(Integer id) throws Exception {return beerRepository.findById(id).orElseThrow(Exception::new);}

    public void insertBeer(Beer beer){beerRepository.save(beer);}

    public void deleteBeer(Integer id){beerRepository.deleteById(id);}
}
