package org.proskura.tictactoeserver.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.proskura.tictactoeserver.model.Figure;
import org.proskura.tictactoeserver.model.Position;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Converter
public class JpaMapConverter implements AttributeConverter<Map<Position, Figure>, String> {
    private final TypeReference<Map<Position, Figure>> typeReference = new TypeReference<>() {};
    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Position, Figure> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
