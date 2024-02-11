package com.tanvir.Tanvir.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Tanvir=new Student(
                    "Tanvir",
                    "tanvir@gmail.com",
                    LocalDate.of(2000, 6, 6)
            );

            repository.saveAll(
                    List.of(Tanvir)
            );
        };
    }
}
