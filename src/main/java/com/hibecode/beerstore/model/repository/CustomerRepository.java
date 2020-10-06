package com.hibecode.beerstore.model.repository;

import com.hibecode.beerstore.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    public Customer findByName(String name);

}
