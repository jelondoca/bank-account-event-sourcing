package com.sergio.services;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.external.WithdrawOrderPlaced;
import com.sergio.model.events.internal.WithdrawOrderAccepted;
import com.sergio.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceUnitTest {

    private AccountService accountServive;
    private AccountRepository accountRepository;
    private EventBus eventBus;

    @Before
    public void init() {
        this.accountRepository = mock(AccountRepository.class);
        this.eventBus = mock(EventBus.class);
        this.accountServive = new AccountService(accountRepository, eventBus);
    }

    @Test
    public void shouldUpdateAccountQuantity() {
        // GIVEN
        when(accountRepository.get(any())).thenReturn(Optional.of(fakeAccount(20f)));
        WithdrawOrderPlaced orderPlaced = new WithdrawOrderPlaced("fakeAccount", 15f);
        orderPlaced.setId("fakeId");
        // WHEN
        this.accountServive.applyOrder(orderPlaced);
        // THEN
        ArgumentCaptor<WithdrawOrderAccepted> captor = ArgumentCaptor.forClass(WithdrawOrderAccepted.class);
        verify(accountRepository).save(eq("fakeAccount"), captor.capture());
        WithdrawOrderAccepted withdrawAccepted = captor.getValue();
        assertThat(withdrawAccepted.getQuantity()).isEqualTo(15f);
    }

    private static AccountInfo fakeAccount(float quantity) {
        AccountInfo info = new AccountInfo();
        info.setId("fakeAccount");
        info.setOwner("fake owner");
        info.setQuantity(quantity);
        return info;
    }
}