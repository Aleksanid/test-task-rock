package com.example.testtask.controllers

import com.example.testtask.models.request.GameProcessorRequest
import com.example.testtask.models.response.GameProcessorResponse
import com.example.testtask.services.GameProcessorService
import com.example.testtask.services.exceptions.GameProcessorServiceException
import com.example.testtask.validators.GameProcessorRequestValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("open-api-games/v1")
class GameProcessorController(val gameProcessorService: GameProcessorService) {

    private val logger: Logger = LoggerFactory.getLogger(GameProcessorController::class.java)

    @PostMapping("/games-processor")
    fun gamesProcessor(@RequestBody request: GameProcessorRequest): GameProcessorResponse {
        logger.info("Received request for Games Processor endpoint")
        val validationError = GameProcessorRequestValidator.validateRequest(request)
        if (validationError != null) {
            logger.error("Request has failed validation with error: $validationError")
            return GameProcessorResponse(request.api, null, validationError, false)
        }
        return gameProcessorService.processRequest(request)
    }

    @ExceptionHandler(GameProcessorServiceException::class)
    fun handleServiceException(gameProcessorServiceException: GameProcessorServiceException): GameProcessorResponse {
        logger.error("Service exception raised", gameProcessorServiceException)
        return GameProcessorResponse(gameProcessorServiceException.apiType, null, gameProcessorServiceException.message
                ?: "Unexpected error while processing request", false)
    }
}