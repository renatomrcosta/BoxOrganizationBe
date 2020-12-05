package com.xunfos.boxorganizationbe.repository

import org.testcontainers.containers.PostgreSQLContainer

fun getDBTestContainer(): KPostgreSQLContainer = KPostgreSQLContainer("postgres:13")
    .withDatabaseName("box-db")
    .withUsername("postgres")
    .withPassword("postgres")

class KPostgreSQLContainer(image: String) : PostgreSQLContainer<KPostgreSQLContainer>(image)
