package com.sergio.services;

import com.sergio.model.AccountInfo;
import com.sergio.model.orders.WithdrawOrderPlaced;
import com.sergio.model.orders.WithdrawOrderRejected;
import com.sergio.repositories.AccountRepository;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class AccountService {

    @Inject
    AccountRepository accountRepository;

    @Inject
    EventBus eventBus;

    public void applyOrder(@Observes WithdrawOrderPlaced order) {
        System.out.println("ORDER PLACED! checking information...");
        String accountId = order.getAccount();
        Optional<AccountInfo> info = accountRepository.get(accountId);
        if(info.isPresent()) {
          // TODO: do the logic
        } else{
            System.err.println("account not found! rejecting...");
            WithdrawOrderRejected rejected = new WithdrawOrderRejected(order.getId(),"account not found");
            eventBus.produce(rejected);
        }
    }

}
