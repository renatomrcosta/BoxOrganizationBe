package com.xunfos.boxorganizationbe.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity

import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfiguration {
    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
    ): SecurityWebFilterChain =
        http.authorizeExchange()
            .pathMatchers("/actuator/*").permitAll()
            .anyExchange().authenticated()
            .and().httpBasic().and().build()
}
