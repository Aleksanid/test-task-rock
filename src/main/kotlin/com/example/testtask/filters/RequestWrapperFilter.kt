package com.example.testtask.filters

import com.example.testtask.models.utility.MultiReadHttpServletRequest
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(-2)
class RequestWrapperFilter : Filter {

    override fun doFilter(
            request: ServletRequest,
            response: ServletResponse,
            chain: FilterChain) {
        val requestWrapper = MultiReadHttpServletRequest(request as HttpServletRequest)
        chain.doFilter(requestWrapper, response)
    }
}