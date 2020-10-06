package com.hibecode.beerstore.resource;

import com.hibecode.beerstore.model.Customer;
import com.hibecode.beerstore.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    public CustomerController(CustomerRepository customerRepository) {

        this.repository = customerRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Customer> findAll() {

        return (List<Customer>) repository.findAll();
    }

    @GetMapping (path = "/{id}")
    @ResponseBody
    public Optional<Customer> findById(@PathVariable Long id) {

        return repository.findById(id);
    }

    @GetMapping (path = "/name/{name}")
    @ResponseBody
    public Customer findByName(@PathVariable String name) {

        return repository.findByName(name);
    }

    @PostMapping
    @ResponseBody
    public Customer create (@RequestParam String name, @RequestParam Integer age) {
        Customer customer = new Customer(name, age);
        if (name != null && name.length() > 0 && age != null && age.intValue() > 0) {
            repository.save(customer);
        }
        return customer;
    }

    @DeleteMapping (path = "/{id}")
    @ResponseBody
    public void deleteId(@PathVariable Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer != null) {
            repository.deleteById(id);
        }
    }
}
