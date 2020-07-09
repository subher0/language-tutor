package org.subher0.language.tutor.mapper

import org.subher0.language.tutor.dto.model.PartOfSpeechDto
import org.subher0.language.tutor.entity.Language
import org.subher0.language.tutor.entity.PartOfSpeech

fun mapPartOfSpeech(partOfSpeechDto: PartOfSpeechDto, language: Language? = null): PartOfSpeech {
    return with(partOfSpeechDto) {
        PartOfSpeech(
                id = id,
                name = name,
                description = description,
                language = language
        )
    }
}

fun mapPartOfSpeech(partOfSpeech: PartOfSpeech): PartOfSpeechDto {
    return with(partOfSpeech) {
        PartOfSpeechDto(
                id = id,
                name = name,
                description = description
        )
    }
}