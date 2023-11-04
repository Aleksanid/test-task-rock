package com.example.testtask.services

import com.example.testtask.models.enums.ApiType
import com.example.testtask.models.request.GameProcessorRequest
import com.example.testtask.models.request.components.data.BalanceRequestData
import com.example.testtask.models.request.components.data.CreditRequestData
import com.example.testtask.models.request.components.data.DebitRequestData
import com.example.testtask.models.response.GameProcessorResponse
import com.example.testtask.models.response.components.data.BalanceResponseData
import com.example.testtask.models.response.components.data.CreditResponseData
import com.example.testtask.models.response.components.data.DebitResponseData
import com.example.testtask.repositories.WalletRepository
import com.example.testtask.services.exceptions.GameProcessorServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GameProcessorService(val walletRepository: WalletRepository) {

    private val logger: Logger = LoggerFactory.getLogger(GameProcessorService::class.java)

    @Transactional
    fun processRequest(request: GameProcessorRequest): GameProcessorResponse {
        logger.info("Processing request for API type: ${request.api}")

        return when (request.api) {
            ApiType.BALANCE -> processBalanceRequest(request.data as BalanceRequestData)
            ApiType.CREDIT -> processCreditRequest(request.data as CreditRequestData)
            ApiType.DEBIT -> processDebitRequest(request.data as DebitRequestData)
            else -> {
                logger.error("Unsupported API type: ${request.api}")
                throw IllegalArgumentException("Api name ${request.api} is not supported here")
            }
        }
    }

    private fun processDebitRequest(requestData: DebitRequestData): GameProcessorResponse {
        logger.info("Processing DEBIT request")

        return try {
            walletRepository.increaseAmount(requestData.userId, requestData.currency.toString(), requestData.amount)
            val amount = walletRepository.findFirstByUserIdAndCurrency(requestData.userId, requestData.currency.toString()).amount
            GameProcessorResponse(ApiType.DEBIT, DebitResponseData(requestData.currency!!, amount))
        } catch (ex: EmptyResultDataAccessException) {
            throw GameProcessorServiceException(ApiType.DEBIT, "Wallet not found for user ${requestData.userId} and currency ${requestData.currency} or resulted in negative balance", ex)
        } catch (ex: Exception) {
            throw GameProcessorServiceException(ApiType.DEBIT, cause = ex)
        }
    }

    private fun processCreditRequest(requestData: CreditRequestData): GameProcessorResponse {
        logger.info("Processing CREDIT request")

        return try {
            walletRepository.decreaseAmount(requestData.userId, requestData.currency.toString(), requestData.amount)
            val amount = walletRepository.findFirstByUserIdAndCurrency(requestData.userId, requestData.currency.toString()).amount
            GameProcessorResponse(ApiType.CREDIT, CreditResponseData(requestData.currency!!, amount))
        } catch (ex: EmptyResultDataAccessException) {
            throw GameProcessorServiceException(ApiType.CREDIT, "Wallet not found for user ${requestData.userId} and currency ${requestData.currency}", ex)
        } catch (ex: Exception) {
            throw GameProcessorServiceException(ApiType.CREDIT, cause = ex)
        }
    }

    private fun processBalanceRequest(requestData: BalanceRequestData): GameProcessorResponse {
        logger.info("Processing BALANCE request")

        return try {
            val amount = walletRepository.findFirstByUserIdAndCurrency(requestData.userId, requestData.currency.toString()).amount
            GameProcessorResponse(ApiType.BALANCE, BalanceResponseData(requestData.currency!!, amount))
        } catch (ex: EmptyResultDataAccessException) {
            throw GameProcessorServiceException(ApiType.BALANCE, "Wallet not found for user ${requestData.userId} and currency ${requestData.currency}", ex)
        } catch (ex: Exception) {
            throw GameProcessorServiceException(ApiType.BALANCE, cause = ex)
        }
    }
}
