package com.example.testtask.services.exceptions;

import com.example.testtask.models.enums.ApiType

class GameProcessorServiceException(val apiType: ApiType, message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}
