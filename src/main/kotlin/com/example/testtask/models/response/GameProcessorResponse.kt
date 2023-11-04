package com.example.testtask.models.response

import com.example.testtask.models.enums.ApiType
import com.example.testtask.models.response.components.data.ResponseData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class GameProcessorResponse(val api: ApiType?, val data: ResponseData?, val errorMessage: String = "NONE", @get:JsonProperty("isSuccess") val isSuccess: Boolean = true) {
}