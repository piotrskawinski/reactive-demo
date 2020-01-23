package com.example.api.service.customer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @JsonProperty("customer_no")
    Long customerNo;

    @JsonProperty("name")
    private String name;

}
