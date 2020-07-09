package org.subher0.language.tutor.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.subher0.language.tutor.entity.Language
import org.subher0.language.tutor.entity.PartOfSpeech

@Repository
interface PartOfSpeechRepository : JpaRepository<PartOfSpeech, Long> {
    fun existsByNameAndLanguage(name: String, language: Language): Boolean
}