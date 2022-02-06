package tk.mwacha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mwacha.mapper.MapperFactory;
import tk.mwacha.mapper.MessageMapper;

@Configuration
public class MapperConfig {

    @Bean
    public MessageMapper mapper() {
        var objectMapper = MapperFactory.create();
        return new MessageMapper(objectMapper);
    }
}
