package com.sergio.repositories;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.internal.DepositOrderAccepted;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryUnitTest {

    private AccountRepository accountRepository;

    @Before
    public void init() {
        this.accountRepository = new AccountRepository();
    }

    @Test
    public void shouldDepositEvent() {
        // GIVEN
        AccountInfo fakeAccount = new AccountInfo();
        fakeAccount.setQuantity(0f);
        fakeAccount.setOwner("fake owner");
        String accountId = this.accountRepository.add(fakeAccount);
        DepositOrderAccepted deposit = new DepositOrderAccepted("fakeId", 15f);
        // WHEN
        this.accountRepository.save(accountId, deposit);
        this.accountRepository.save(accountId, deposit);
        // THEN
        AccountInfo account = this.accountRepository.get(accountId).get();
        assertThat(account.getQuantity()).isEqualTo(30f);
    }

}