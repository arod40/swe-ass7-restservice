package spring_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   // <1>
public class SampleSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSpringApplication.class, args); // <2>
    }

}
