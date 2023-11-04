package com.example.testtask.models.entities;

import jakarta.persistence.*

@Entity
@Table(name = "Wallets")
class Wallet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "WalletID")
        var walletId: Long,
        @Column(name = "UserID")
        var userId: Long,
        @Column(name = "Currency")
        var currency: String,
        @Column(name = "Amount")
        var amount: Long
)