package com.example.testtask.models.response.components.data

import com.example.testtask.models.enums.Currency
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class ResponseData(val currency: Currency, val amount: Long) {
}