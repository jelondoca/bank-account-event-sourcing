package com.sergio.repositories;

import com.sergio.model.AccountInfo;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class AccountRepository {

    private static final Map<String, AccountInfo> accounts = new ConcurrentHashMap<>();


    public Optional<AccountInfo> get(String accountId) {
        AccountInfo accountInfo = accounts.get(accountId);
        if (accountInfo == null) {
            return Optional.empty();
        } else {
            return Optional.of(accountInfo);
        }
    }
}
