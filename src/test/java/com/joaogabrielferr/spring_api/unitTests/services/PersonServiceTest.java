package com.joaogabrielferr.spring_api.unitTests.services;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.exceptions.RequiredObjectIsNullException;
import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.repositories.PersonRepository;
import com.joaogabrielferr.spring_api.services.PersonService;
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
        List<Person> personList = input.mockEntityList();

        when(repository.findAll()).thenReturn(personList);

        List<PersonVO> people = service.findAll();

        assertNotNull(people);
        assertEquals(10,people.size());

        for(int i = 0;i<10;i++){
        assertNotNull(people.get(i).getMyId());
        assertNotNull(people.get(i).getLinks());
        assertTrue(people.get(i).toString().contains(String.format("links: [</api/person/v1/%d>;rel=\"self\"]", people.get(i).getMyId())));
        assertEquals(people.get(i).getMyId(), personList.get(i).getId());
        assertEquals(people.get(i).getEmail(),personList.get(i).getEmail());
        assertEquals(people.get(i).getFirstName(),personList.get(i).getFirstName());
        assertEquals(people.get(i).getLastName(),personList.get(i).getLastName());
        assertEquals(people.get(i).getAddress(),personList.get(i).getAddress());

        }

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
    void createWithNullPerson(){
        Exception ex = assertThrows(RequiredObjectIsNullException.class,()->{
            service.create(null);
        });

        String expectedMessage = "Can't persist a null object!";
        String message = ex.getMessage();

        assertTrue(message.contains(expectedMessage));
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

    @Test
    void UpdateWithNullPerson(){
        Exception ex = assertThrows(RequiredObjectIsNullException.class,()->{
            service.update(null);
        });

        String expectedMessage = "Can't persist a null object!";
        String message = ex.getMessage();

        assertTrue(message.contains(expectedMessage));
    }


}