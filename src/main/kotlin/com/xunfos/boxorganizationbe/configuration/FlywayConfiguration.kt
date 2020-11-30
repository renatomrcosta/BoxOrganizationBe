package com.xunfos.boxorganizationbe.configuration

import org.flywaydb.core.Flyway
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfiguration(
    private val flywayProperties: FlywayProperties
) {
    init {
        migrateDB()
    }

    private final fun migrateDB() {
        logger.info("Preparing DB Migration")
        try {
            with(flywayProperties) {
                Flyway
                    .configure()
                    .baselineOnMigrate(isBaselineOnMigrate)
                    .dataSource(url, user, password)
                    .load()
                    .migrate()
            }

            logger.info("DB Migration completed successfully")
        } catch (e: Exception) {
            logger.error("error migrating the db", e)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.simpleName)
    }
}
