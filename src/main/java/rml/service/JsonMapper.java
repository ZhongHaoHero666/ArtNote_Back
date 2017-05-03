package rml.service;

import java.io.IOException;
import java.util.Map;

public interface JsonMapper {
	public <T> T fromJsonString(String jsonString, Class<T> clazz);

	public String toJsonString(Object object) throws IOException;

	public <T extends Map> T toMap(String jsonString);
}
