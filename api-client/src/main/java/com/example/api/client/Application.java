package com.example.api.client;

import com.example.api.client.demo.Demo;
import com.example.api.client.demo.LoadDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        // Demo demo = new SimpleDemo();
        // Demo demo = new ParallelDemo();
        // Demo demo = new AsyncDemo();
        Demo demo = new LoadDemo(1000);
        demo.run();
    }

}

