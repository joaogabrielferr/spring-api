package com.joaogabrielferr.spring_api.mapper;

import com.joaogabrielferr.spring_api.data.VO.v1.PersonVO;
import com.joaogabrielferr.spring_api.model.Person;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static final ModelMapper mapper = new ModelMapper();

    static{
        mapper.createTypeMap(Person.class, PersonVO.class).addMapping(Person::getId,PersonVO::setMyId);
        mapper.createTypeMap(PersonVO.class, Person.class).addMapping(PersonVO::getMyId,Person::setId);
    }

    public static <S,D> D parseObject(S source, Class<D> destination){
        return mapper.map(source,destination);
    }

    public static <S,D> List<D> parseListObject(List<S> sourceList, Class<D> destination){
        List<D> destinationObjects = new ArrayList<D>();
        for(S source : sourceList){
            destinationObjects.add(mapper.map(source,destination));
        }
        return destinationObjects;
    }

}
