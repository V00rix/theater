package theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"theater.config", "theater.rest", "theater.repositories", "theater.domain.entities"})

public class TheaterApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TheaterApplication.class, args);
    }
}
