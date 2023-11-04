package com.example.testtask.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "security")
class SecurityConfiguration @ConstructorBinding constructor(val signSecret: String, val headerName: String, val algorithm: String) {
}