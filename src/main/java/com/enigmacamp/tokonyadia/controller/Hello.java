package com.enigmacamp.tokonyadia.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Hello {

    // http:localhost:8080/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello(){
        return "Hello batch 9!";
    }

    @GetMapping("/")
    public String getAnotherHello(){
        return "Hello again batch 9!";
    }

    //path variable
    @GetMapping("/users/{id}")
    public String pathVar(@PathVariable String id){
        return "User id : "+ id;
    }

    //Request param
    @GetMapping("/users")
    public String reqParam(@RequestParam(name = "page") String page){
        return "Hasil req params: "+ page;
    }

    @GetMapping("/users/inactive")
    public String reqParam2(@RequestParam(name = "page") String page){
        return "Hasil req params: "+ page;
    }

    @PostMapping("/users")
    public String reqBody(@RequestBody HashMap<String, String> map){
        return "req body: " + map;
    }
}
