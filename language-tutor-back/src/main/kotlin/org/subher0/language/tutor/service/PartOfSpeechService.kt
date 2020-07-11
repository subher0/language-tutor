package org.subher0.language.tutor.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.subher0.language.tutor.constant.ValidationError
import org.subher0.language.tutor.dto.model.PartOfSpeechDto
import org.subher0.language.tutor.dto.response.CountableDto
import org.subher0.language.tutor.dto.response.EntityDto
import org.subher0.language.tutor.mapper.mapPartOfSpeech
import org.subher0.language.tutor.repository.LanguageRepository
import org.subher0.language.tutor.repository.PartOfSpeechRepository
import javax.transaction.Transactional

@Service
class PartOfSpeechService @Autowired constructor(
        private val partOfSpeechRepository: PartOfSpeechRepository,
        private val languageRepository: LanguageRepository
) {
    @Transactional
    fun createPartOfSpeech(partOfSpeechDto: PartOfSpeechDto): ResponseEntity<EntityDto<PartOfSpeechDto>> {
        return languageRepository.findByIsActiveTrue()?.let { language ->
            if (partOfSpeechRepository.existsByNameAndLanguage(partOfSpeechDto.name!!, language)) {
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityDto(
                        errorInfo = ValidationError.PART_OF_SPEECH_UNIQUE_CONSTRAINT_VIOLATION.toErrorInfo()))
            } else {
                var partOfSpeech = mapPartOfSpeech(partOfSpeechDto, language)
                partOfSpeech = partOfSpeechRepository.save(partOfSpeech)
                ResponseEntity.ok(EntityDto(data = mapPartOfSpeech(partOfSpeech)))
            }
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(EntityDto(errorInfo = ValidationError.NO_ACTIVE_LANGUAGE.toErrorInfo()))
    }

    fun listPartsOfSpeech(): ResponseEntity<CountableDto<PartOfSpeechDto>> {
        return languageRepository.findByIsActiveTrue()?.let { language ->
            partOfSpeechRepository.findAllByLanguage(language).map {
                mapPartOfSpeech(it)
            }.let {
                ResponseEntity.ok(CountableDto(data = it))
            }
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CountableDto(errorInfo = ValidationError.NO_ACTIVE_LANGUAGE.toErrorInfo()))
    }
}