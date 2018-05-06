package com.sergio.services;

import com.sergio.model.AccountInfo;
import com.sergio.model.orders.WithdrawOrderAccepted;
import com.sergio.model.orders.WithdrawOrderPlaced;
import com.sergio.model.orders.WithdrawOrderRejected;
import com.sergio.repositories.AccountRepository;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Optional;
import java.util.UUID;

public class AccountService {

    @Inject
    AccountRepository accountRepository;

    @Inject
    EventBus eventBus;

    public String create(String ownerName, String ownerSurnames) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setOwner(ownerName + " " + ownerSurnames);
        accountInfo.setQuantity(0.0f);
        return accountRepository.add(accountInfo);
    }

    private void applyOrder(@Observes WithdrawOrderPlaced order) {
        System.out.println("ORDER PLACED! checking information...");
        String accountId = order.getAccount();
        Optional<AccountInfo> info = accountRepository.get(accountId);
        if(info.isPresent()) {
            AccountInfo account = info.get();
            Float currentQuantity = account.getQuantity();
            Float quantity = order.getQuantity();
            float updatedQuantity = currentQuantity - quantity;
            if(updatedQuantity < 0) {
                System.err.println("insufficient balance! rejecting");
                WithdrawOrderRejected rejected = new WithdrawOrderRejected(order.getId(),"insufficient balance");
                eventBus.produce(rejected);
                return;
            }
            account.setQuantity(updatedQuantity);
            accountRepository.update(account);
            WithdrawOrderAccepted orderAccepted = new WithdrawOrderAccepted();
            eventBus.produce(orderAccepted);
        } else{
            System.err.println("account not found! rejecting...");
            WithdrawOrderRejected rejected = new WithdrawOrderRejected(order.getId(),"account not found");
            eventBus.produce(rejected);
        }
    }

    public AccountInfo get(String accountId) {
        return accountRepository.get(accountId).orElseThrow(NotFoundException::new);
    }
}
