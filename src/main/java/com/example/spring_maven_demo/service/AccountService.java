package com.example.spring_maven_demo.service;

import com.example.spring_maven_demo.entity.Account;
import com.example.spring_maven_demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount() {

        // Создаем новый объект Account
        long count = accountRepository.count() + 1;
        String name = "user " + count;
        BigDecimal amount = new BigDecimal(count);
        Account newAccount = new Account(name, amount);

        // Сохраняем в базу данных
        return accountRepository.save(newAccount);
    }

    public List<Account> findByMixedCriteria(String client, Double amount) {
        if (client != null && amount == null) {
            return accountRepository.findByClient(client);
        } else if (client == null && amount != null) {
            return accountRepository.findByAmount(amount);
        } else if (client != null && amount != null) {
            return accountRepository.findByClientOrAmount(client, amount);
        } else {
            throw new IllegalArgumentException("At least one parameter must be provided");
        }
    }

    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Optional<Account> findByIdIfExists(Long id) {
        return accountRepository.findByIdWithSkipLock(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> updateAccountIfExists(Long id, Account accountDetails, Integer sleep) throws InterruptedException {
        Optional<Account> account = findByIdIfExists(id);
        if (!account.isPresent()) {
            return null;
        }
        return Optional.ofNullable(updateAccount(account.get(), accountDetails, sleep));
    }

    public Account updateAccount(Long id, Account accountDetails, Integer sleep) throws InterruptedException {
        Account account = getById(id);
        return updateAccount(account, accountDetails, sleep);
    }

    public Account updateAccount(Account account, Account accountDetails, Integer sleep) throws InterruptedException {
        account.setClient(accountDetails.getClient());
        account.setAmount(accountDetails.getAmount());
        if (sleep != null && sleep > 0) {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return accountRepository.save(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> findByClient(String client) {
        return accountRepository.findByClient(client);
    }

    public List<Account> findByAmount(Double amount) {
        return accountRepository.findByAmount(amount);
    }

    public List<Account> findByAmountBetween(Double minAmount, Double maxAmount) {
        return accountRepository.findByAmountBetween(minAmount, maxAmount);
    }

    public List<Account> findByClientLike(String term) {
        return accountRepository.findByClientLike(term);
    }
}
