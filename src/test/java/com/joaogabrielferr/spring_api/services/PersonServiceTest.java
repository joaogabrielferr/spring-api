package com.joaogabrielferr.spring_api.services;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.repositories.PersonRepository;
import com.joaogabrielferr.spring_api.unitTests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity();
        person.setId(1L);
        person.setEmail("test@test.com");

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), person.getId());
        assertEquals(result.getEmail(),person.getEmail());
        assertEquals(result.getFirstName(),person.getFirstName());
        assertEquals(result.getLastName(),person.getLastName());
        assertEquals(result.getAddress(),person.getAddress());

    }

    @Test
    void findAll() {
//        List<Person> persons = input.mockEntityList();
//
//        when(repository.findAll()).thenReturn(persons);
//

    }

    @Test
    void create() {

        Person entity = input.mockEntity(1);
        Person persisted = input.mockEntity(1);


        PersonVO vo = input.mockVO(1);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), persisted.getId());
        assertEquals(result.getEmail(), persisted.getEmail());
        assertEquals(result.getFirstName(), persisted.getFirstName());
        assertEquals(result.getLastName(), persisted.getLastName());
        assertEquals(result.getAddress(), persisted.getAddress());
    }


    @Test
    void update() {

        Person entity = input.mockEntity(1);
        Person persisted = input.mockEntity(1);


        PersonVO vo = input.mockVO(1);
        vo.setMyId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getMyId());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals(result.getMyId(), persisted.getId());
        assertEquals(result.getEmail(), persisted.getEmail());
        assertEquals(result.getFirstName(), persisted.getFirstName());
        assertEquals(result.getLastName(), persisted.getLastName());
        assertEquals(result.getAddress(), persisted.getAddress());

    }

}