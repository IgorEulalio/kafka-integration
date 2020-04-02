package com.study.kafka.gateway;

import com.study.kafka.controller.model.Score;
import com.study.kafka.controller.model.Student;

import java.util.List;

public interface StudentGateway {
    List<Student> getStudents();
    void addStudent(Student student);
    Student getStudentById(int id);
    void addScore(Score score);
    List<Score> getScores();
}
