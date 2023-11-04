package com.example.testtask.repositories;

import com.example.testtask.models.entities.Wallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface WalletRepository : JpaRepository<Wallet, Long> {

    fun findFirstByUserIdAndCurrency(userId: Long, currency: String): Wallet

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w SET w.amount = w.amount + :increaseBy WHERE w.userId = :userId AND w.currency = :currency")
    fun increaseAmount(userId: Long, currency: String, increaseBy: Long)

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w SET w.amount = w.amount - :decreaseBy WHERE w.userId = :userId AND w.currency = :currency AND w.amount >= :decreaseBy")
    fun decreaseAmount(userId: Long, currency: String, decreaseBy: Long)
}