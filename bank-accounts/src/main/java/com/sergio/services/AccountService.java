package com.sergio.services;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.external.DepositOrderPlaced;
import com.sergio.model.events.external.WithdrawOrderPlaced;
import com.sergio.model.events.external.DepositOrderAccepted;
import com.sergio.model.events.external.WithdrawOrderAccepted;
import com.sergio.model.events.external.WithdrawOrderRejected;
import com.sergio.repositories.AccountRepository;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;
    private final EventBus eventBus;

    @Inject
    public AccountService(AccountRepository repository, EventBus eventBus) {
        this.accountRepository = repository;
        this.eventBus = eventBus;
    }

    public String create(String ownerName, String ownerSurnames) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setOwner(ownerName + " " + ownerSurnames);
        accountInfo.setQuantity(0.0f);
        return accountRepository.add(accountInfo);
    }

    public AccountInfo get(String accountId) {
        return accountRepository.get(accountId).orElseThrow(NotFoundException::new);
    }

    void applyOrder(@Observes WithdrawOrderPlaced order) {
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
            com.sergio.model.events.internal.WithdrawOrderAccepted internalWithdrawAccepted = new com.sergio.model.events.internal.WithdrawOrderAccepted(order.getId(), order.getQuantity());
            WithdrawOrderAccepted externalOrderAccepted = new WithdrawOrderAccepted(order.getId());
            accountRepository.save(accountId, internalWithdrawAccepted);
            eventBus.produce(externalOrderAccepted);
        } else{
            reject(order.getId(), "account not found");
        }
    }

    void depositOrder(@Observes DepositOrderPlaced depositOrder) {
        System.out.println("DEPOSIT ORDER PLACED! checking information...");
        String accountId = depositOrder.getAccountId();
        Optional<AccountInfo> info = accountRepository.get(accountId);
        if(info.isPresent()) {
            AccountInfo account = info.get();
            Float currentQuantity = account.getQuantity();
            Float quantity = depositOrder.getQuantity();
            float updatedQuantity = currentQuantity + quantity;
            account.setQuantity(updatedQuantity);
            DepositOrderAccepted externalDepositOrderAccepted = new DepositOrderAccepted(depositOrder.getId());
            com.sergio.model.events.internal.DepositOrderAccepted orderAccepted = new com.sergio.model.events.internal.DepositOrderAccepted(depositOrder.getId(), depositOrder.getQuantity());
            accountRepository.save(accountId, orderAccepted);
            eventBus.produce(externalDepositOrderAccepted);
        } else{
            reject(depositOrder.getId(), "account not found = " + depositOrder.getAccountId());
        }
    }

    private void reject(String id, String reason) {
        System.err.println(reason);
        WithdrawOrderRejected rejected = new WithdrawOrderRejected(id, reason);
        eventBus.produce(rejected);
    }

    private static boolean insufficientBalance(float updatedQuantity) {
        return updatedQuantity < 0;
    }
}
