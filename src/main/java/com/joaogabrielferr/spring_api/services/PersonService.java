package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.exceptions.ResourceNotFoundException;
import com.joaogabrielferr.spring_api.mapper.ObjectMapper;
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


    public PersonVO findById(Long id){
        logger.info("Finding one person");
        var entity =  repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        return ObjectMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all");

        return ObjectMapper.parseListObject(repository.findAll(),PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person");
        var entity = ObjectMapper.parseObject(person,Person.class);
        return ObjectMapper.parseObject(repository.save(entity),PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating one person");

        var entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + person.getId() + " does not exist"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());

        return ObjectMapper.parseObject(repository.save(entity),PersonVO.class);



    }

    public void delete(Long id){

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " does not exist"));

        repository.delete(entity);
    }



}
