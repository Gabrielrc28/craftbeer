package com.beerhouse.rest;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class BeerRestController {
    BeerService beerService;

    @GetMapping(value = "/beers",  produces = "application/json")
    public ResponseEntity<List<Beer>> getAllBeers (){
        return new ResponseEntity<>(beerService.getAllBeers(), HttpStatus.OK);
    }

    @PostMapping(value = "/beers",  produces = "application/json")
    public ResponseEntity<Beer> saveBeer(@RequestBody Beer beer){
        beerService.insertBeer(beer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/beers/",  produces = "application/json")
    public ResponseEntity<Beer> getBeerById(@RequestParam Integer id) throws Exception {
        return new ResponseEntity<>(beerService.getBeerById(id),HttpStatus.OK);
    }

    @PutMapping(value = "/beers/", produces = "application/json")
    public ResponseEntity<HttpStatus> putBeerById(@RequestParam Integer id,@RequestBody Beer beer) throws Exception {
        beerService.updateBeerById(beer,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/beers/", produces = "application/json")
    public ResponseEntity<String> patchBeerById(@RequestParam Integer id,@RequestBody Beer beer) throws Exception {
        beerService.updateBeerById(beer,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/beers/",  produces = "application/json")
    public ResponseEntity<Beer> deleteBeerById(@RequestParam Integer id){
        beerService.deleteBeer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
