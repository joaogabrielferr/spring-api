package com.joaogabrielferr.spring_api.controllers;

import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id")Long id){
        return personService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return personService.create(person);
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person,@PathVariable(value = "id")String id){
        Person p = personService.update(person);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
