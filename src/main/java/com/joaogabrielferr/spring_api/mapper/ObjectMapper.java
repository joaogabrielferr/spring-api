package com.joaogabrielferr.spring_api.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    private static final ModelMapper mapper = new ModelMapper();

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
