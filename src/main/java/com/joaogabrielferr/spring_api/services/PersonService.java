package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    public Person findById(String id){
        logger.info("Finding one person");
        Person person =  new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("João");
        person.setLastName("Ferreira");
        person.setAddress("Minas Gerais");
        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all");

        List<Person> persons = new ArrayList<>();
        for(int i = 0;i<10;i++){
            Person p = new Person();
            p.setId(counter.incrementAndGet());
            p.setFirstName("Fulano " + String.valueOf(i));
            p.setLastName("Silva Sauro");
            p.setAddress("Minas Gerais");
            persons.add(p);
        }

        return persons;
    }

    public Person create(Person person){
        logger.info("Creating one person");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person");
        return person;
    }

    public Boolean delete(String id){
        logger.info("Deleting person");
        return true;
    }



}
