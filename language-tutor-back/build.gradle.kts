import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

sourceSets.main {
	java.srcDirs("src/main/kotlin")
}

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.72"
}

configurations {
	implementation.get().exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
}

group = "org.subher0.language.tutor"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	val springFoxVersion = "2.9.2"
	val log4jVersion = "2.13.3"

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.infinispan:infinispan-cachestore-remote") {
		version {
			strictly("9.4.18.Final")
		}
	}
	implementation("org.infinispan:infinispan-core") {
		version {
			strictly("9.4.18.Final")
		}
	}
	implementation("org.infinispan:infinispan-commons") {
		version {
			strictly("9.4.18.Final")
		}
	}
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
	implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")


	implementation("io.springfox:springfox-swagger-ui:$springFoxVersion")
	implementation("io.springfox:springfox-swagger2:$springFoxVersion")

	// keycloak
	implementation("org.jboss.resteasy:resteasy-jackson2-provider:3.11.2.Final")
	implementation("org.keycloak:keycloak-dependencies-server-all:10.0.2@pom") {
		isTransitive = true
	}
	implementation("org.keycloak:keycloak-spring-boot-starter:10.0.2") {
		isTransitive = true
	}
	implementation("com.nimbusds:oauth2-oidc-sdk:8.16")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.liquibase:liquibase-core")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}
