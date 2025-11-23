package pl.investtrack.investtrack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class InvestTrackApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Hello World!");
    }

    public static void main(String[] args) {
        SpringApplication.run(InvestTrackApplication.class, args);
    }
}

