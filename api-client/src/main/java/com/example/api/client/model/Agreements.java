package com.example.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Agreements {
    @JsonProperty("customer_no")
    Long customerNo;

    @JsonProperty("accounts")
    List<BankAccount> accounts = new ArrayList<>();

    @JsonProperty("pension_policies")
    List<PensionPolicy> pensionPolicies = new ArrayList<>();

    @JsonProperty("insurance_policies")
    List<InsurancePolicy> insurancePolicies = new ArrayList<>();

    public void addAccounts(List<BankAccount> bankAccounts) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.addAll(bankAccounts);
    }

    public void addInsurancePolicies(List<InsurancePolicy> policies) {
        if (insurancePolicies == null) {
            insurancePolicies = new ArrayList<>();
        }
        insurancePolicies.addAll(policies);
    }

    public void addPensionPolicies(List<PensionPolicy> policies) {
        if (pensionPolicies == null) {
            pensionPolicies = new ArrayList<>();
        }
        pensionPolicies.addAll(policies);
    }

    public long totalAgreements() {
        return accounts.size() + insurancePolicies.size() + pensionPolicies.size();
    }

}
