package com.joaogabrielferr.spring_api.controllers;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.data.VO.v2.PersonVOV2;
import com.joaogabrielferr.spring_api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person") public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id")Long id){
        return personService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        return personService.create(person);
    }

    @PostMapping(value = "/v2",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personService.createV2(person);
    }


    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonVO> update(@RequestBody PersonVO person,@PathVariable(value = "id")String id){
        PersonVO p = personService.update(person);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
