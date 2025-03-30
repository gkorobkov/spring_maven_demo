package com.example.spring_maven_demo.controller;

import com.example.spring_maven_demo.entity.Account;
import com.example.spring_maven_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Получение всех счетов
    @GetMapping
    public List<Account> getAllAccounts() {
        return (List<Account>) accountService.findAll();
    }

    // Получение счёта по ID
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getById(id);
    }

    // Создание нового счёта
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    // Обновление счёта
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id,
        @RequestParam(required = false) Integer sleep, 
        @RequestBody Account accountDetails) throws InterruptedException {
        return accountService.updateAccount(id, accountDetails, sleep);
    }

    @PutMapping("/skiplocked/{id}")
    public Optional<Account> updateAccountWithSkipLocked(@PathVariable Long id,
        @RequestParam(required = false) Integer sleep, 
        @RequestBody Account accountDetails) throws InterruptedException {
        return accountService.updateAccountWithSkipLock(id, accountDetails, sleep);
    }

    @PutMapping("/PessimisticLock/{id}")
    public Optional<Account> updateAccountWithPessimisticLock(@PathVariable Long id,
        @RequestParam(required = false) Integer sleep, 
        @RequestBody Account accountDetails) throws InterruptedException {
        return accountService.updateAccountWithPessimisticLock(id, accountDetails, sleep);
    }

    // Удаление счёта
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    // Методы поиска

    // Поиск по клиенту
    @GetMapping("/search/client/{client}")
    public List<Account> findByClient(@PathVariable String client) {
        return accountService.findByClient(client);
    }

    // Поиск по сумме
    @GetMapping("/search/amount/{amount}")
    public List<Account> findByAmount(@PathVariable Double amount) {
        return accountService.findByAmount(amount);
    }

    // Поиск по диапазону суммы
    @GetMapping("/search/range")
    public List<Account> findByRange(@RequestParam Double minAmount, @RequestParam Double maxAmount) {
        return accountService.findByAmountBetween(minAmount, maxAmount);
    }

    // Поиск по частичному совпадению клиента
    @GetMapping("/search/clientlike/{term}")
    public List<Account> findByClientLike(@PathVariable String term) {
        return accountService.findByClientLike(term);
    }

    // Поиск по клиенту или сумме
    @GetMapping("/search/mixed")
    public List<Account> findByMixedCriteria(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) Double amount) {

        return accountService.findByMixedCriteria(client, amount);
    }

}
