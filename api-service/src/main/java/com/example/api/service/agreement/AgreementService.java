
package com.example.api.service.agreement;

import com.example.api.service.agreement.model.Agreements;
import com.example.api.service.agreement.model.BankAccount;
import com.example.api.service.agreement.model.InsurancePolicy;
import com.example.api.service.agreement.model.PensionPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AgreementService {

    private static final String LEGACY_BASE_URL = "http://localhost:8082";
    private static final WebClient client = WebClient.create(LEGACY_BASE_URL);

    public Mono<Agreements> getAgreements(Long customerNo) {
        Flux<BankAccount> bankAccountFlux = client.get()
                .uri("/bank/accounts/" + customerNo)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BankAccount.class);

        Flux<InsurancePolicy> insurancePolicyFlux = client.get()
                .uri("/insurance/policies/" + customerNo)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(InsurancePolicy.class);

        Flux<PensionPolicy> pensionPolicyFlux = client.get()
                .uri("/pension/policies/" + customerNo)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PensionPolicy.class);

        return bankAccountFlux.collectList()
                .zipWith(insurancePolicyFlux.collectList(), (bankAccounts, insurancePolicies) -> {
                            Agreements agreements = new Agreements();
                            agreements.setCustomerNo(customerNo);
                            agreements.addAccounts(bankAccounts);
                            agreements.addInsurancePolicies(insurancePolicies);
                            return agreements;
                        }
                ).zipWith(pensionPolicyFlux.collectList(), (agreements, policies) -> {
                    agreements.addPensionPolicies(policies);
                    return agreements;
                });
    }

}