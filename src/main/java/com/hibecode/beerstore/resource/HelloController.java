package com.hibecode.beerstore.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping
    @ResponseBody
    public String hello() {
        return "Hello Sring Boot";
    }

    @GetMapping(path = "/say/{name}")
    @ResponseBody
    public String say(@PathVariable String name) {
        return "Hello " + name + " ao Sring Boot.";
    }

    @PostMapping(path = "/say")
    @ResponseBody
    public String sayPost(@RequestParam String name) {
        return "Hello " + name + " ao Sring Boot.";
    }
}
