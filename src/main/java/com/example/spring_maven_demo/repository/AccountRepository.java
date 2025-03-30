package com.example.spring_maven_demo.repository;

import com.example.spring_maven_demo.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    //@NativeQuery(value = "")
    //Account getAccountWithSkipLock() ;


    // Поиск по клиенту
    List<Account> findByClient(String client);

    // Поиск по сумме
    List<Account> findByAmount(Double amount);

    // Поиск по диапазону суммы
    List<Account> findByAmountBetween(Double minAmount, Double maxAmount);

    // Поиск по части названия клиента
    List<Account> findByClientLike(String searchTerm);

    // Поиск по нескольким условиям
    @Query("SELECT a FROM Account a WHERE LOWER(a.client) LIKE %?1% OR a.amount = ?2")
    List<Account> findByClientOrAmount(String clientSearch, Double amount);
}

