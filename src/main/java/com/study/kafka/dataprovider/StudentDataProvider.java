package com.study.kafka.dataprovider;

import com.study.kafka.controller.model.Score;
import com.study.kafka.controller.model.Student;
import com.study.kafka.gateway.StudentGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataProvider implements StudentGateway {

    public List<Student> students = new ArrayList<>();

    public List<Score> scores = new ArrayList<>();

    public List<Student> getStudents(){
        return this.students;
    }

    public void addStudent(Student student){
        student.setId(students.size() + 1);
        students.add(student);
    }

    @Override
    public Student getStudentById(int id) {
        try{
            return students.stream().filter(st -> st.getId() == id).findFirst().get();
        }
        catch (Exception ex){
            System.out.println("Problema ao encontrar o aluno de id:" + id);
        }
        return null;
    }

    @Override
    public void addScore(Score score) {
        score.setId(scores.size() + 1);
        this.scores.add(score);
        this.getStudentById(score.getStudentId()).setScore(score);
    }

    @Override
    public List<Score> getScores() {
        return this.scores;
    }
}
