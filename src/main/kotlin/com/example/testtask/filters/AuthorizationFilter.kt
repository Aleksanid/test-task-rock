package com.example.testtask.filters;

import com.example.testtask.configurations.SecurityConfiguration
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import java.security.MessageDigest

@Component
@Order(-1)
class AuthorizationFilter(val securityConfiguration: SecurityConfiguration) : Filter {

    private val logger: Logger = LoggerFactory.getLogger(AuthorizationFilter::class.java)

    override fun doFilter(
            request: ServletRequest,
            response: ServletResponse,
            chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val content = getContent(httpRequest)

        val signHeader = httpRequest.getHeader(securityConfiguration.headerName)
        if (signHeader != null && validateSignature(content, signHeader)) {
            chain.doFilter(httpRequest, response)
        } else {
            logger.error("Request has failed sign check")
            httpResponse.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }

    private fun getContent(request: HttpServletRequest): String {
        return try {
            return request.inputStream.bufferedReader().use { it.readText() }
        } catch (_: IOException) {
            ""
        }
    }

    private fun validateSignature(content: String, actualSign: String): Boolean {
        val combined = content + securityConfiguration.signSecret
        val md5sum = MessageDigest.getInstance(securityConfiguration.algorithm).digest(combined.toByteArray()).joinToString("") { "%02x".format(it) }
        return md5sum == actualSign
    }
}
