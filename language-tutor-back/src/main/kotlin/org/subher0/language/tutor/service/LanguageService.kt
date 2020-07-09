package org.subher0.language.tutor.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.subher0.language.tutor.constant.ValidationError
import org.subher0.language.tutor.dto.model.LanguageDto
import org.subher0.language.tutor.dto.response.CountableDto
import org.subher0.language.tutor.dto.response.EmptyResponse
import org.subher0.language.tutor.dto.response.EntityDto
import org.subher0.language.tutor.mapper.mapLanguage
import org.subher0.language.tutor.repository.LanguageRepository
import javax.transaction.Transactional

@Service
class LanguageService @Autowired constructor(
        private val languageRepository: LanguageRepository
){
    @Transactional
    fun createLanguage(languageDto: LanguageDto): ResponseEntity<EntityDto<LanguageDto>> {
        val existingLanguage = languageRepository.findByCode(languageDto.code!!)
        return when {
            existingLanguage != null -> {
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(EntityDto(
                                errorInfo = ValidationError.LANGUAGE_UNIQUE_CONSTRAINT_VIOLATION.toErrorInfo()))
            }
            languageRepository.existsByIsActiveTrue() -> {
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(EntityDto(
                                errorInfo = ValidationError.LANGUAGE_UNIQUE_ACTIVE_VIOLATION.toErrorInfo()))
            }
            else -> {
                val language = mapLanguage(languageDto)
                languageRepository.save(language)
                ResponseEntity.ok(EntityDto(data = mapLanguage(language)))
            }
        }
    }

    fun listLanguages(): ResponseEntity<CountableDto<LanguageDto>> {
        return languageRepository.findAll().map {
            mapLanguage(it)
        }.let {
            ResponseEntity.ok(CountableDto(it))
        }
    }

    @Transactional
    fun activateLanguage(id: Long): ResponseEntity<EmptyResponse> {
        val currentActiveLanguage = languageRepository.findByIsActiveTrue()
        if (currentActiveLanguage != null) {
            languageRepository.switchActivationStatus(currentActiveLanguage.id!!, false)
        }
        languageRepository.switchActivationStatus(id, true)
        return ResponseEntity.ok(EmptyResponse())
    }

    fun getActiveLanguage(): ResponseEntity<EntityDto<LanguageDto>> {
        return when (val language = languageRepository.findByIsActiveTrue()) {
            null -> {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(EntityDto(errorInfo = ValidationError.NO_ACTIVE_LANGUAGE.toErrorInfo()))
            }
            else -> {
                ResponseEntity.ok(EntityDto(data = mapLanguage(language)))
            }
        }
    }
}