package com.study.kafka.controller.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class Student {

    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int age;
    private University university;

}
