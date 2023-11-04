package com.example.testtask.models.response.components.data

import com.example.testtask.models.enums.Currency

class CreditResponseData(currency: Currency, amount: Long) : ResponseData(currency, amount) {
}