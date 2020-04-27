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
public class Payment {

    @JsonProperty("order_id")
    @NotNull
    private int orderId;

    @NotNull
    @JsonProperty("credit_card")
    private int creditCard;

    @JsonProperty("fraud")
    private boolean isFraud;
}
