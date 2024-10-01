package com.joaogabrielferr.spring_api.unitTests.mapper;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.mapper.ObjectMapper;
import com.joaogabrielferr.spring_api.model.Person;
import com.joaogabrielferr.spring_api.unitTests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMapperTests {

    MockPerson inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockPerson();
    }


    @Test
    public void parseEntityToVOTest(){
        PersonVO output = ObjectMapper.parseObject(inputObject.mockEntity(),PersonVO.class);
        assertEquals(Long.valueOf(0L),output.getId());
        assertEquals("First name test0",output.getFirstName());
        assertEquals("Last name test0",output.getLastName());
        assertEquals("Address test0",output.getAddress());
    }

    @Test
    public void parseVOToEntity(){
        Person output = ObjectMapper.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L),output.getId());
        assertEquals("First name test0",output.getFirstName());
        assertEquals("Last name test0",output.getLastName());
        assertEquals("Address test0",output.getAddress());
    }
}
