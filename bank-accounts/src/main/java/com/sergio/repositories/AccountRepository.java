package com.sergio.repositories;

import com.sergio.model.AccountInfo;
import com.sergio.model.events.internal.AccountEvent;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
public class AccountRepository {

    private static final Map<String, AccountInfo> accounts = new ConcurrentHashMap<>();
    private static final Map<String, List<AccountEvent>> events = new ConcurrentHashMap<>();

    public void save(String accountId, AccountEvent order) {
        events.computeIfAbsent(accountId, k -> new ArrayList<>());
        events.computeIfPresent(accountId, (k,v) -> { v.add(order); return v; });
    }

    private static class EventProcessor {
        private final AccountInfo accountInfo;
        EventProcessor(AccountInfo accountInfo) {
            this.accountInfo = accountInfo;
        }
        void process(List<AccountEvent> allEvents) {
            allEvents.forEach(ev -> ev.apply(accountInfo));
        }
    }

    public Optional<AccountInfo> get(String accountId) {
        AccountInfo accountInfo = accounts.get(accountId);
        if (accountInfo == null) {
            return Optional.empty();
        } else {
            List<AccountEvent> allEvents = events.getOrDefault(accountId, Collections.emptyList());
            EventProcessor processor = new EventProcessor(accountInfo);
            processor.process(allEvents);
            return Optional.of(accountInfo);
        }
    }

    public String add(AccountInfo accountInfo) {
        String id = UUID.randomUUID().toString();
        accountInfo.setId(id);
        accounts.put(id, accountInfo);
        return id;
    }
}
