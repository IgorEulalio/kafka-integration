package com.study.kafka.controller.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Valid
public class Student {

    private int id;
    private String name;
    private int age;
    private Score score;

    public Student(){
    }

    private static final AtomicInteger count = new AtomicInteger(1);

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Score getScore() { return score; }

    public Student(@NotNull int id, @NotNull String name, @NotNull int age, Score score) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.age = age;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setScore(Score score) {this.score = score; };
}
