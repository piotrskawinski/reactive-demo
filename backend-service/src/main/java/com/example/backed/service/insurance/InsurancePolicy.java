package com.example.backed.service.insurance;

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

    public static InsurancePolicy newPolicy(long customerNo, long policyId) {
        return new InsurancePolicy(policyId, customerNo, "Insurance policy {" + customerNo + ":" + policyId + "}");
    }
}
