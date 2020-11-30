package com.xunfos.boxorganizationbe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FlywayProperties::class)
@ConfigurationPropertiesScan
class BoxOrganizationBeApplication

fun main(args: Array<String>) {
    runApplication<BoxOrganizationBeApplication>(*args)
}
