package com.example.testtask

import com.example.testtask.configurations.SecurityConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableConfigurationProperties(SecurityConfiguration::class)
@EnableJpaRepositories
class TestTaskApplication

fun main(args: Array<String>) {
    runApplication<TestTaskApplication>(*args)
}
