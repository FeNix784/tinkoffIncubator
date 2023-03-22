package ru.tkachev.tinkoffIncubator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tkachev.tinkoffIncubator.service.JDBCService;

@SpringBootApplication
public class TinkoffIncubatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffIncubatorApplication.class, args);
    }

}
