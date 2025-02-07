package com.example.demo.Student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudent(){
        return List.of(new Student(
                1L,
                "Prithvi",
                LocalDate.of(2000, Month.SEPTEMBER, 28),
                "prithvirohira8@gmail.com",
                21
        ), new Student(
                2L,
                "Samveg",
                LocalDate.of(2000, Month.SEPTEMBER, 28),
                "prithvirohira8@gmail.com",
                22
        ));
    }
}
