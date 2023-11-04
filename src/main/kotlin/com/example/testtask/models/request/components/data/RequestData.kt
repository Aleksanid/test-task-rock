package com.example.testtask.models.request.components.data

import com.example.testtask.models.enums.Currency
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = BalanceRequestData::class, name = "balance"),
        JsonSubTypes.Type(value = CreditRequestData::class, name = "credit"),
        JsonSubTypes.Type(value = DebitRequestData::class, name = "debit")
)
abstract class RequestData(val userId: Long, val currency: Currency?) {
}