package rml.service.impl;

import java.io.IOException;
import java.util.Map;







import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import rml.service.JsonMapper;
@Service("jsonMapper")
public class JacksonMapper implements JsonMapper{
	private final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    @Override
    public <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return DEFAULT_MAPPER.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toJsonString(Object object) throws IOException {
        try {
            return DEFAULT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    @Override
    public <T extends Map> T toMap(String jsonString) {
        return (T) fromJsonString(jsonString, Map.class);
    }
}
