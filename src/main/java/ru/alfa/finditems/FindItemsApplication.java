package ru.alfa.finditems;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindItemsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FindItemsApplication.class, args);
    }

    @Value()

    @Override
    public void run(String... args) throws Exception {

    }
}
