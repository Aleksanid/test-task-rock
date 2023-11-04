package com.example.testtask.validators;

import com.example.testtask.models.enums.ApiType
import com.example.testtask.models.enums.Currency
import com.example.testtask.models.request.GameProcessorRequest
import com.example.testtask.models.request.components.data.BalanceRequestData
import com.example.testtask.models.request.components.data.CreditRequestData
import com.example.testtask.models.request.components.data.DebitRequestData

object GameProcessorRequestValidator {
    fun validateRequest(gameProcessorRequest: GameProcessorRequest): String? {
        if (gameProcessorRequest.api == null) {
            return "Provided api name is unknown. Supported values: [${ApiType.values().joinToString()}]"
        }
        if (gameProcessorRequest.data == null) {
            return "Data value is missing from request body"
        }
        if (gameProcessorRequest.data.currency == null) {
            return "Provided currency name is unknown. Supported values: [${Currency.values().joinToString()}]"
        }
        when (gameProcessorRequest.api) {
            ApiType.BALANCE -> {
                validateBalanceRequestData(gameProcessorRequest.data as BalanceRequestData)
            }

            ApiType.CREDIT -> {
                validateCreditRequestData(gameProcessorRequest.data as CreditRequestData)
            }

            ApiType.DEBIT -> {
                validateDebitRequestData(gameProcessorRequest.data as DebitRequestData)
            }

            else -> {
                null
            }
        }?.let { return it }

        return null
    }

    private fun validateDebitRequestData(debitRequestData: DebitRequestData): String? {
        return if (debitRequestData.amount <= 0) {
            "Amount for debit request must be positive and bigger than 0"
        } else {
            null
        }
    }

    private fun validateCreditRequestData(creditRequestData: CreditRequestData): String? {
        return if (creditRequestData.amount <= 0) {
            "Amount for credit request must be positive and bigger than 0"
        } else {
            null
        }
    }

    private fun validateBalanceRequestData(balanceRequestData: BalanceRequestData): String? {
        return null
    }
}
