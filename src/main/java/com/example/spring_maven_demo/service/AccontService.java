package com.example.spring_maven_demo.service;

import com.example.spring_maven_demo.entity.Account;
import com.example.spring_maven_demo.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccontService {

    private AccountRepository accountRepository;

//    public Optional<Account> getUserById(@NonNull Long id) {
//        return Optional.ofNullable(accountRepository.findAllById());
//    }
}
