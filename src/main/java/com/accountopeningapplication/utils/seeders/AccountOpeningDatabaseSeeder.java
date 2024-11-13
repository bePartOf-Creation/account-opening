package com.accountopeningapplication.utils.seeders;

import com.accountopeningapplication.enums.AccountType;
import com.accountopeningapplication.enums.Tierlevel;
import com.accountopeningapplication.repositories.AccountRepository;
import com.accountopeningapplication.repositories.CustomerRepository;
import com.accountopeningapplication.utils.builders.Builder;
import com.accountopeningapplication.utils.constants.StringValues;
import com.accountopeningapplication.utils.nuban.Nuban;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static com.accountopeningapplication.enums.AccountType.SAVINGS;
import static com.accountopeningapplication.enums.Tierlevel.TIER_2;
import static com.accountopeningapplication.utils.constants.StringValues.DATABASE_SEEDING_ERROR;

@Configuration @Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountOpeningDatabaseSeeder {

    private final Nuban nuban;
    private final AccountRepository accountRepo;
    private final CustomerRepository customerRepo;

    @PostConstruct
    @Transactional
    public void seedDatabase() {
        try {
            //seed
            var existingCustomer = Builder.buildExistingCustomer(
                    "Ola",
                    "Sunkanmi",
                    "+234808569091",
                    "olasunkanmi@3line.ng.com"
            );

            var savedCustomer = this.customerRepo.save(existingCustomer);

            var existingCustomerAccount = Builder.buildCustomerAccount(
                    savedCustomer, 5000.0,
                    nuban.generateMerchantNuban(),
                    TIER_2, SAVINGS);

            var savedAccount = this.accountRepo.save(existingCustomerAccount);
            savedCustomer.getAccounts().add(savedAccount);
            this.customerRepo.save(savedCustomer);

        } catch (Exception exception){
            log.info(DATABASE_SEEDING_ERROR, exception.getMessage());
            log.debug(DATABASE_SEEDING_ERROR, exception.getMessage());
        }
    }
}
