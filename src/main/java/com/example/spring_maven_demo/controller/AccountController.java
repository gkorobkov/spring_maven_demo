package com.example.spring_maven_demo.controller;


import com.example.spring_maven_demo.entity.Account;
import com.example.spring_maven_demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Получение всех счетов
    @GetMapping
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    // Получение счёта по ID
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Создание нового счёта
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    // Обновление счёта
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        account.setClient(accountDetails.getClient());
        account.setAmount(accountDetails.getAmount());

        return accountRepository.save(account);
    }

    // Удаление счёта
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }

    // Методы поиска

    // Поиск по клиенту
    @GetMapping("/search/client/{client}")
    public List<Account> findByClient(@PathVariable String client) {
        return accountRepository.findByClient(client);
    }

    // Поиск по сумме
    @GetMapping("/search/amount/{amount}")
    public List<Account> findByAmount(@PathVariable Double amount) {
        return accountRepository.findByAmount(amount);
    }

    // Поиск по диапазону суммы
    @GetMapping("/search/range")
    public List<Account> findByRange(@RequestParam Double minAmount, @RequestParam Double maxAmount) {
        return accountRepository.findByAmountBetween(minAmount, maxAmount);
    }

    // Поиск по частичному совпадению клиента
    @GetMapping("/search/clientlike/{term}")
    public List<Account> findByClientLike(@PathVariable String term) {
        return accountRepository.findByClientLike(term);
    }

    // Поиск по клиенту или сумме
    @GetMapping("/search/mixed")
    public List<Account> findByMixedCriteria(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) Double amount
    ) {
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


}

