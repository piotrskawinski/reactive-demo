package com.example.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @JsonProperty
    Long id;

    @JsonProperty("customer_no")
    Long customerNo;

    @JsonProperty
    String description;
}
