package com.beerhouse;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
    private  BeerService beerService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initDB();
    }

    public void initDB(){
        beerService.insertBeer(Beer.builder().name("Maniacs").alcoholContent("4,7%").id(1).category("IPA").price(9).build());
        beerService.insertBeer(Beer.builder().name("Unicorn Witbier").alcoholContent("5%").id(2).category("Witbier").price(6).build());
        beerService.insertBeer(Beer.builder().name("Campinas").alcoholContent("4,7%").id(3).category("Pilsen").price(7).build());
        beerService.insertBeer(Beer.builder().name("Estrella Galicia").alcoholContent("6,5%").id(4).category("Helles Bock").price(10).build());
        beerService.insertBeer(Beer.builder().name("King Octopus").alcoholContent("6,5%").id(5).category("Sour Ale").price(30).build());
    }
}