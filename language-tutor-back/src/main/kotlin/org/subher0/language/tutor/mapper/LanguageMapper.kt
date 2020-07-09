package org.subher0.language.tutor.mapper

import org.subher0.language.tutor.dto.model.LanguageDto
import org.subher0.language.tutor.entity.Language

fun mapLanguage(languageDto: LanguageDto): Language {
    return with(languageDto) {
        Language(
                id = id,
                name = name,
                code = code?.toUpperCase(),
                description = description,
                isActive = isActive
        )
    }
}

fun mapLanguage(language: Language): LanguageDto {
    return with(language) {
        LanguageDto(
                id = id,
                name = name,
                code = code,
                description = description,
                isActive = isActive
        )
    }
}