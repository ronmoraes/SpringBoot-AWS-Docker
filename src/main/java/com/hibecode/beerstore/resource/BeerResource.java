package com.hibecode.beerstore.resource;

import com.hibecode.beerstore.model.Beer;
import com.hibecode.beerstore.model.repository.Beers;
import com.hibecode.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerResource {

    @Autowired(required = true)
    private Beers beersRepository;

    @Autowired(required = true)
    private BeerService beerService;

    @GetMapping
    public List<Beer> all() {
        return beersRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beer create(@Valid @RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable Long id, @Valid @RequestBody Beer beer) {
        beer.setId(id);
        return beerService.save(beer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        beerService.delete(id);
    }
}
