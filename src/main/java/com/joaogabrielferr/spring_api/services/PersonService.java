package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.exceptions.ResourceNotFoundException;
import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {


    @Autowired
    PersonRepository repository;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    public Person findById(Long id){
        logger.info("Finding one person");
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

    }

    public List<Person> findAll(){
        logger.info("Finding all");

        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating one person");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person");

        var entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + person.getId() + " does not exist"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());

        return repository.save(person);



    }

    public Boolean delete(Long id){

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " does not exist"));

        repository.delete(entity);

        return true;
    }



}
