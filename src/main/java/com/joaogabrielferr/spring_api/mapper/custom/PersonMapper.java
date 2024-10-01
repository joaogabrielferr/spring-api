package com.joaogabrielferr.spring_api.mapper.custom;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.data.VO.v2.PersonVOV2;
import com.joaogabrielferr.spring_api.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {


    public PersonVOV2 convertEntityToVO(Person person){

        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setEmail(person.getEmail());
        //if Person entity had a birthday field
        //vo.setBirthday(person.getBirthday());

        return vo;
    }

    public Person convertVOtoEntity(PersonVOV2 person){

        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setEmail(person.getEmail());
        //if Person entity had a birthday field
        //entity.setBirthday(person.getBirthday());


        return entity;
    }

}
