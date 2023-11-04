package com.example.testtask.models.request.components.data

import com.example.testtask.models.enums.Currency

class CreditRequestData(val amount: Long, userId: Long, currency: Currency) : RequestData(userId, currency) {
}