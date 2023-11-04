package com.example.testtask.models.request

import com.example.testtask.models.enums.ApiType
import com.example.testtask.models.request.components.data.RequestData
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonIgnoreProperties(ignoreUnknown = true)
data class GameProcessorRequest(val api: ApiType?,
                                @JsonTypeInfo(
                                        use = JsonTypeInfo.Id.NAME,
                                        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
                                        property = "api",
                                        defaultImpl = Void::class)
                                val data: RequestData?)