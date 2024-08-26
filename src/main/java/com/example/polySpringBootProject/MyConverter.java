package com.example.polySpringBootProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class MyConverter implements AttributeConverter<List<String> , String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if(attribute ==null || attribute.isEmpty()){
            System.out.println("여기 걸린다1");
            return null;
        }
        try {
            System.out.println("여기 걸린다2");
            return mapper.writeValueAsString(attribute);
        }
        catch (JsonProcessingException e){
            System.out.println("여기 걸린다3");
            throw new RuntimeException("Error converting List to JSON", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if(dbData == null || dbData.isEmpty()){
            return null;
        }
        try {
            return mapper.readValue(dbData, new TypeReference<List<String>>() {});
        }catch (JsonProcessingException e){
            throw new RuntimeException("Error converting JSON to List", e);
        }
    }
}