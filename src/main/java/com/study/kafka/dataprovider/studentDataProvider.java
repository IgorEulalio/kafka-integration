package com.study.kafka.dataprovider;

import com.study.kafka.controller.model.Student;

import java.util.List;

public class studentDataProvider {

    public List<Student> students;

    public List<Student> getStudents(){
        return this.students;
    }

    public void addStudent(Student student){
        students.add(student);
    }
}
