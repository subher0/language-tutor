package org.subher0.language.tutor.config.keycloak

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters
import org.keycloak.services.filters.KeycloakSessionServletFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.naming.*
import javax.naming.spi.InitialContextFactory
import javax.naming.spi.NamingManager
import javax.servlet.Filter
import javax.sql.DataSource


@Configuration
class EmbeddedKeycloakConfig {
    @Bean
    @Throws(Exception::class)
    fun keycloakJaxRsApplication(
            keycloakServerProperties: KeycloakServerProperties, dataSource: DataSource): ServletRegistrationBean<*> {
        mockJndiEnvironment(dataSource)
        EmbeddedKeycloakApplication.keycloakServerProperties = keycloakServerProperties
        val servlet = ServletRegistrationBean(
                HttpServlet30Dispatcher())
        servlet.addInitParameter("javax.ws.rs.Application",
                EmbeddedKeycloakApplication::class.java.name)
        servlet.addInitParameter(ResteasyContextParameters.RESTEASY_SERVLET_MAPPING_PREFIX,
                keycloakServerProperties.contextPath)
        servlet.addInitParameter(ResteasyContextParameters.RESTEASY_USE_CONTAINER_FORM_PARAMS,
                "true")
        servlet.addUrlMappings(keycloakServerProperties.contextPath + "/*")
        servlet.setLoadOnStartup(1)
        servlet.isAsyncSupported = true
        return servlet
    }

    @Bean
    fun keycloakSessionManagement(
            keycloakServerProperties: KeycloakServerProperties): FilterRegistrationBean<*> {
        val filter = FilterRegistrationBean<Filter>()
        filter.setName("Keycloak Session Management")
        filter.filter = KeycloakSessionServletFilter()
        filter.addUrlPatterns(keycloakServerProperties.contextPath + "/*")
        return filter
    }

    @Throws(NamingException::class)
    private fun mockJndiEnvironment(dataSource: DataSource) {
        NamingManager.setInitialContextFactoryBuilder {
            InitialContextFactory {
                object : InitialContext() {
                    override fun lookup(name: Name): Any? {
                        return lookup(name.toString())
                    }

                    override fun lookup(name: String): Any? {
                        if ("spring/datasource" == name) {
                            return dataSource
                        }
                        return null
                    }

                    override fun getNameParser(name: String): NameParser {
                        return NameParser { n: String? -> CompositeName(n) }
                    }

                    override fun close() {}
                }
            }
        }
    }
}