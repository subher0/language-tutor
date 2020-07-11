package org.subher0.language.tutor.config.keycloak

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "keycloak-embedded.server")
class KeycloakServerProperties {
    var contextPath = "/language-tutor/auth"
    var realmImportFile = "language-tutor-realm.json"
    var adminUser = AdminUser()

    // getters and setters

    // getters and setters
    class AdminUser {
        var username = "admin"
        var password = "admin" // getters and setters
    }
}