package com.sergio.services;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.DepositOrderAccepted;
import com.sergio.model.events.DepositOrderPlaced;
import com.sergio.model.events.WithdrawOrderAccepted;
import com.sergio.model.events.WithdrawOrderPlaced;
import com.sergio.model.events.WithdrawOrderRejected;
import com.sergio.repositories.AccountRepository;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

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

    public AccountInfo get(String accountId) {
        return accountRepository.get(accountId).orElseThrow(NotFoundException::new);
    }

    private void applyOrder(@Observes WithdrawOrderPlaced order) {
        System.out.println("WITHDRAW ORDER PLACED! checking information...");
        String accountId = order.getAccount();
        Optional<AccountInfo> info = accountRepository.get(accountId);
        if(info.isPresent()) {
            AccountInfo account = info.get();
            Float currentQuantity = account.getQuantity();
            Float quantity = order.getQuantity();
            float updatedQuantity = currentQuantity - quantity;
            if(insufficientBalance(updatedQuantity)) {
                reject(order.getId(), "insufficient balance");
                return;
            }
            account.setQuantity(updatedQuantity);
            accountRepository.update(account);
            WithdrawOrderAccepted orderAccepted = new WithdrawOrderAccepted(order.getId());
            eventBus.produce(orderAccepted);
        } else{
            reject(order.getId(), "account not found");
        }
    }

    private void depositOrder(@Observes DepositOrderPlaced depositOrder) {
        System.out.println("DEPOSIT ORDER PLACED! checking information...");
        String accountId = depositOrder.getAccountId();
        Optional<AccountInfo> info = accountRepository.get(accountId);
        if(info.isPresent()) {
            AccountInfo account = info.get();
            Float currentQuantity = account.getQuantity();
            Float quantity = depositOrder.getQuantity();
            float updatedQuantity = currentQuantity + quantity;
            account.setQuantity(updatedQuantity);
            accountRepository.update(account);
            DepositOrderAccepted orderAccepted = new DepositOrderAccepted(depositOrder.getId());
            eventBus.produce(orderAccepted);
        } else{
            reject(depositOrder.getId(), "account not found = " + depositOrder.getAccountId());
        }
    }

    private void reject(String id, String reason) {
        System.err.println("account not found! rejecting...");
        WithdrawOrderRejected rejected = new WithdrawOrderRejected(id, reason);
        eventBus.produce(rejected);
    }

    private static boolean insufficientBalance(float updatedQuantity) {
        return updatedQuantity < 0;
    }
}
