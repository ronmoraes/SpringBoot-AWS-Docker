package com.hibecode.beerstore.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import com.hibecode.beerstore.model.Beer;
import com.hibecode.beerstore.model.BeerType;
import com.hibecode.beerstore.model.repository.Beers;
import com.hibecode.beerstore.service.exception.BeerAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class BeerServiceTest {

    private BeerService beerService;

    @Mock
    private Beers beersMocked;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        beerService = new BeerService(beersMocked);
    }

    @Test(expected = BeerAlreadyExistException.class)
    public void should_deny_creation_of_beer_that_exists() {
        Beer beerInDataBase = new Beer();
        beerInDataBase.setName("Heineken");
        beerInDataBase.setType(BeerType.LAGER);
        beerInDataBase.setVolume(new BigDecimal("355"));
        when(beersMocked.findByNameAndType("Heineken", BeerType.LAGER)).thenReturn(Optional.of(beerInDataBase));

        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));
        beerService.save(newBeer);
    }

    @Test
    public void should_create_new_beer() {
        Beer newBeer = new Beer();
        newBeer.setName("Heineken");
        newBeer.setType(BeerType.LAGER);
        newBeer.setVolume(new BigDecimal("355"));

        Beer newBeerInDataBase = new Beer();
        newBeerInDataBase.setId(10L);
        newBeerInDataBase.setName("Heineken");
        newBeerInDataBase.setType(BeerType.LAGER);
        when(beersMocked.save(newBeer)).thenReturn(newBeerInDataBase);
        Beer beerSaved = beerService.save(newBeer);

        assertThat(beerSaved.getId(), equalTo(10L));
        assertThat(beerSaved.getName(), equalTo("Heineken"));
        assertThat(beerSaved.getType(), equalTo(BeerType.LAGER));
    }
}
