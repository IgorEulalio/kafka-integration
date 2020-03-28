package com.study.kafka.dataprovider;

import com.study.kafka.controller.model.Student;
import com.study.kafka.gateway.StudentGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataProvider implements StudentGateway {

    public List<Student> students = new ArrayList<>();

    public List<Student> getStudents(){
        return this.students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    @Override
    public Student getStudentById() {
        return null;
    }
}
