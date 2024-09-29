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

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id")String id){
        return personService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
        return personService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person){
        return personService.create(person);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> update(@RequestBody Person person,@PathVariable(value = "id")String id){
        Person p = personService.update(person);
        return ResponseEntity.ok(p);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id")String id){
        return ResponseEntity.ok(personService.delete((id)));
    }





}
