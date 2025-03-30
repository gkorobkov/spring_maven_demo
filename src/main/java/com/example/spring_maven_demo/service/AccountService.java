package com.example.spring_maven_demo.service;

import com.example.spring_maven_demo.entity.Account;
import com.example.spring_maven_demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

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

}
