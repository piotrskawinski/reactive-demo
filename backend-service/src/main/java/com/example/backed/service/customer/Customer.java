package com.example.backed.service.customer;

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

    public static Customer newCustomer(long customerNo) {
        return new Customer(customerNo, "Customer {" + customerNo + "}");
    }

}
