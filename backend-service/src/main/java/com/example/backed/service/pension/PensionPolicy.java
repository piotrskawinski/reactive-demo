package com.example.backed.service.pension;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionPolicy {
    @JsonProperty
    Long id;

    @JsonProperty("customer_no")
    Long customerNo;

    @JsonProperty
    String description;

    public static PensionPolicy newPolicy(long customerNo, long policyId) {
        return new PensionPolicy(policyId, customerNo, "Pension policy {" + customerNo + ":" + policyId + "}");
    }
}
