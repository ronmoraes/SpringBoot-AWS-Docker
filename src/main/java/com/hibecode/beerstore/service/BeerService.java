package com.hibecode.beerstore.service;

import com.hibecode.beerstore.model.Beer;
import com.hibecode.beerstore.model.repository.Beers;
import com.hibecode.beerstore.service.exception.BeerAlreadyExistException;
import com.hibecode.beerstore.service.exception.BeerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeerService {

    private Beers beers;

    public BeerService(@Autowired Beers beers) {
        this.beers = beers;
    }

    public Beer save(final Beer beer) {
        verifyIfBeerExists(beer);
        return beers.save(beer);
    }

    public void delete(final Long id) {
        final Optional beer = beers.findById(id);
        if(!beer.isPresent()) {
            throw new BeerNotFoundException();
        }

        beers.deleteById(id);
    }

    private void verifyIfBeerExists(final Beer beer) {
        Optional<Beer> beerByNameAndType = beers.findByNameAndType
                (beer.getName(), beer.getType());

        if (beerByNameAndType.isPresent() && (beer.isNew() ||
                isUpdatingToADifferentBeer(beer, beerByNameAndType))) {
            throw new BeerAlreadyExistException();
        }
    }

    private boolean isUpdatingToADifferentBeer(Beer beer, Optional<Beer> beerByNameAndType) {
        return beer.alreadyExist() && !beerByNameAndType.get().equals(beer);
    }
}
