package com.satal.tehnologi.services;

import com.satal.tehnologi.module.Student;
import com.satal.tehnologi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentDataGenerator {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDataGenerator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



//    @EventListener(ApplicationReadyEvent.class)
    public void generateDataOnStartup(){

        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            student.setName("Student_" + i);
            student.setEMail("st0" + i + "@mail.ru");
            student.setPhoneNumber("8-999-228-" + (1234 + i * 927));
            studentRepository.save(student);
        }
    }

}
