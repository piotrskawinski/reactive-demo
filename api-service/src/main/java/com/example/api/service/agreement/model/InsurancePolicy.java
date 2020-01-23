package com.example.api.service.agreement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicy {
    @JsonProperty
    Long id;

    @JsonProperty("customer_no")
    Long customerNo;

    @JsonProperty
    String description;
}
