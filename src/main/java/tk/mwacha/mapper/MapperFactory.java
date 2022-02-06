package tk.mwacha.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MapperFactory {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper create() {
        return MAPPER;
    }
}
