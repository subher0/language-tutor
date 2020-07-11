package org.subher0.language.tutor

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.subher0.language.tutor.config.keycloak.KeycloakServerProperties


@SpringBootApplication
@EnableConfigurationProperties(KeycloakServerProperties::class)
class LanguageTutorApplication {
	companion object {
		@JvmStatic fun main(args: Array<String>) {
			runApplication<LanguageTutorApplication>(*args)
		}
	}

	private val LOG: Logger = LogManager.getLogger(LanguageTutorApplication::class.java)

	@Bean
	fun onApplicationReadyEventListener(
			serverProperties: ServerProperties, keycloakServerProperties: KeycloakServerProperties): ApplicationListener<ApplicationReadyEvent?>? {
		return ApplicationListener {
			val port = serverProperties.port
			val keycloakContextPath = keycloakServerProperties.contextPath
			LOG.info("Embedded Keycloak started: http://localhost:{}{} to use keycloak",
					port, keycloakContextPath)
		}
	}
}
