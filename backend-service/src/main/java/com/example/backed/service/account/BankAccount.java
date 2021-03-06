package com.example.backed.service.account;

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

    public static BankAccount newAccount(long customerNo, long accountId) {
        return new BankAccount(accountId, customerNo, "Bank account {" + customerNo + ":" + accountId + "}");
    }
}
