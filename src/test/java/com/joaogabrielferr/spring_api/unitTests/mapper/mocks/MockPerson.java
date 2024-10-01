package com.joaogabrielferr.spring_api.unitTests.mapper.mocks;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity(){
        return mockEntity(0);
    }

    public PersonVO mockVO(){
        return mockVO(0);
    }

    public Person mockEntity(Integer id){
        Person p = new Person();
        p.setFirstName("First name test" + id);
        p.setLastName("Last name test" + id);
        p.setAddress("Address test" + id);
        p.setId(id.longValue());
        return p;
    }

    public List<Person> mockEntityList(){
        List<Person> l = new ArrayList<>();
        for(int i = 0;i<10;i++){
            l.add(mockEntity(i));
        }
        return l;
    }

    public PersonVO mockVO(Integer id){
        PersonVO p = new PersonVO();
        p.setFirstName("First name test" + id);
        p.setLastName("Last name test" + id);
        p.setAddress("Address test" + id);
        p.setId(id.longValue());
        return p;
    }

    public List<PersonVO> mockVOList(){
        List<PersonVO> l = new ArrayList<>();
        for(int i = 0;i<10;i++){
            l.add(mockVO(i));
        }
        return l;
    }

}
