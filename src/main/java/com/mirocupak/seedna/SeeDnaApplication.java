package com.mirocupak.seedna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeeDnaApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(SeeDnaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SeeDnaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Application started...");
    }
}
