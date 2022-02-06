package tk.mwacha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableConfigurationProperties
@SpringBootApplication
@EntityScan(basePackages = "tk.mwacha.entities")
@EnableJpaRepositories(basePackages = "tk.mwacha.repositories")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}