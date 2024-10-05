package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.controllers.PersonController;
import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.data.VO.v2.PersonVOV2;
import com.joaogabrielferr.spring_api.exceptions.ResourceNotFoundException;
import com.joaogabrielferr.spring_api.mapper.ObjectMapper;
import com.joaogabrielferr.spring_api.mapper.custom.PersonMapper;
import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {


    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    public PersonVO findById(Long id){
        logger.info("Finding one person");
        var entity =  repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        PersonVO vo = ObjectMapper.parseObject(entity,PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll(){
        logger.info("Finding all");

        List<PersonVO> persons =  ObjectMapper.parseListObject(repository.findAll(),PersonVO.class);

        persons.forEach(p->{
            p.add(linkTo(methodOn(PersonController.class).findById(p.getMyId())).withSelfRel());
        });

        return persons;
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person");

        var entity = ObjectMapper.parseObject(person,Person.class);
        PersonVO vo = ObjectMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getMyId())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person){
        logger.info("Creating one person with create v2");
        var entity = personMapper.convertVOtoEntity(person);
        return personMapper.convertEntityToVO(repository.save(entity));
    }

    public PersonVO update(PersonVO person){
        logger.info("Updating one person");

        var entity = repository.findById(person.getMyId())
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + person.getMyId() + " does not exist"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());

        PersonVO vo = ObjectMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getMyId())).withSelfRel());
        return vo;
    }

    public void delete(Long id){

        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id " + id + " does not exist"));

        repository.delete(entity);
    }



}
