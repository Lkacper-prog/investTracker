package pl.investtrack.investtrack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class InvestTrackApplication  {
    public static void main(String[] args) {
        SpringApplication.run(InvestTrackApplication.class, args);
    }
}

