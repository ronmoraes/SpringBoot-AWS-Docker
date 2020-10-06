package com.hibecode.beerstore.model.repository;

import com.hibecode.beerstore.model.Beer;
import com.hibecode.beerstore.model.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Beers extends JpaRepository<Beer, Long> {

    Optional<Beer> findByNameAndType(String name, BeerType type);
}
