package com.example.testtask.models.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class ApiType(@JsonValue private val apiName: String) {
    BALANCE("balance"),
    CREDIT("credit"),
    DEBIT("debit");

    companion object {
        @JvmStatic
        @JsonCreator
        fun getByApiName(apiName: String): ApiType? {
            return ApiType.values().firstOrNull { a -> a.apiName == apiName }
        }
    }
}