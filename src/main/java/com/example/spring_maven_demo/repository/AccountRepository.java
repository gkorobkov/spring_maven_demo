package com.example.spring_maven_demo.repository;

import com.example.spring_maven_demo.entity.Account;

import jakarta.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Поиск по клиенту
    List<Account> findByClient(String client);

    // Поиск по сумме
    List<Account> findByAmount(Double amount);

    // Поиск по диапазону суммы
    List<Account> findByAmountBetween(Double minAmount, Double maxAmount);

    // Поиск по части названия клиента
    List<Account> findByClientLike(String searchTerm);

    // Поиск по нескольким условиям
    @Query("SELECT a FROM Account a WHERE LOWER(a.client) LIKE %?1% OR a.amount = ?2 ") //FOR UPDATE SKIP LOCKED
    List<Account> findByClientOrAmount(String clientSearch, Double amount);

    //@Query("SELECT a FROM Account a WHERE a.id = ?1 for update skip locked")
    //Optional<Account> findByIdWithSkipLock(Long id);

    // использует нативный SQL-запрос с FOR UPDATE SKIP LOCKED
    @Query(value = "SELECT * FROM accounts WHERE id = ?1 FOR UPDATE SKIP LOCKED", nativeQuery = true) //FOR UPDATE SKIP LOCKED
    Optional<Account> findByIdWithSkipLock(Long id);

    // использует пессимистичную блокировку строки при чтении.
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT a FROM Account a WHERE a.id = ?1")
    Optional<Account> findByIdForUpdate(Long id);

}
