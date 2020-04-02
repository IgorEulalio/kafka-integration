package com.study.kafka.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Score {

    @NotNull
    private int id;

    @JsonProperty("student_id")
    @NotNull
    private int studentId;

    @NotNull
    private int points;
}
