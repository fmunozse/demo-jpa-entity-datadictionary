package cloud.fmunozse.demojpaentitydatadictionary.model;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import javax.persistence.*;
import java.io.IOException;

@Data
@Entity
public class Payment {

    @Transient
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Convert(converter = DataDictionaryConverter.class)
    private JsonNode dataDictionary;


    public Payment() {
//       this.dataDictionary = mapper.createObjectNode();
    }


    public void setPayloadDataDictionaryFromObject (Object o) {
        this.dataDictionary = objectMapper.valueToTree(o);
    }

    public <T> T getPayloadDataDirectoryTyped (Class<T> clazz) {
        try {
            return objectMapper.treeToValue(this.dataDictionary, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDataDictionaryString (DDFieldsISO DDFields) {
        return dataDictionary.at(DDFields.path()).asText(null);
    }

    public void setDataDictionaryString (String path, String value) {

        //TODO - Investigate how can create multinodes in one shot

        if (this.dataDictionary == null) {
            this.dataDictionary = objectMapper.createObjectNode();
        }

        JsonPointer valueNodePointer = JsonPointer.compile(path);
        JsonPointer containerPointer = valueNodePointer.head();
        ObjectNode objectNode = (ObjectNode) this.dataDictionary.at(containerPointer);

        String fieldName = valueNodePointer.last().toString();
        fieldName = fieldName.replace("/", "");

        objectNode.put(fieldName,value);
    }


}
