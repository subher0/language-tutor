package org.subher0.language.tutor.config.keycloak

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.keycloak.Config
import org.keycloak.representations.idm.RealmRepresentation
import org.keycloak.services.managers.ApplianceBootstrap
import org.keycloak.services.managers.RealmManager
import org.keycloak.services.resources.KeycloakApplication
import org.keycloak.services.util.JsonConfigProviderFactory
import org.keycloak.util.JsonSerialization
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.util.*


class EmbeddedKeycloakApplication : KeycloakApplication() {
    override fun loadConfig() {
        val factory: JsonConfigProviderFactory = RegularJsonConfigProviderFactory()
        Config.init(factory.create()
                .orElseThrow { NoSuchElementException("No value present") })
    }

    private fun createMasterRealmAdminUser() {
        val session = getSessionFactory().create()
        val applianceBootstrap = ApplianceBootstrap(session)
        val admin = keycloakServerProperties!!.adminUser
        try {
            session.transactionManager.begin()
            applianceBootstrap.createMasterRealmUser(admin.username, admin.password)
            session.transactionManager.commit()
        } catch (ex: Exception) {
            LOG.warn("Couldn't create keycloak master admin user: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }

    private fun createLanguageTutorRealm() {
        val session = getSessionFactory().create()
        try {
            session.transactionManager.begin()
            val manager = RealmManager(session)
            val realmImportFile: Resource = ClassPathResource(keycloakServerProperties!!.realmImportFile)
            manager.importRealm(
                    JsonSerialization.readValue(realmImportFile.inputStream, RealmRepresentation::class.java))
            session.transactionManager.commit()
        } catch (ex: Exception) {
            LOG.warn("Failed to import Realm json file: {}", ex.message)
            session.transactionManager.rollback()
        }
        session.close()
    }

    companion object {
        private val LOG: Logger = LogManager.getLogger(EmbeddedKeycloakApplication::class.java)
        var keycloakServerProperties: KeycloakServerProperties? = null
    }

    init {
        createMasterRealmAdminUser()
        createLanguageTutorRealm()
    }
}