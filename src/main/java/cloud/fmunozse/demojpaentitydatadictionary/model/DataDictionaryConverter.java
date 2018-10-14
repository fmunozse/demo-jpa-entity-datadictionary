package cloud.fmunozse.demojpaentitydatadictionary.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class DataDictionaryConverter implements AttributeConverter<JsonNode,String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(JsonNode attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonNode convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readTree(dbData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
