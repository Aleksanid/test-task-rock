package com.example.testtask.models.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class Currency {
    BTC, USD, GPB, EUR;


    companion object {
        @JvmStatic
        @JsonCreator
        fun getByName(name: String): Currency? {
            return Currency.values().firstOrNull { a -> a.name == name }
        }
    }
}