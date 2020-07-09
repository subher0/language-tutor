package org.subher0.language.tutor.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.subher0.language.tutor.entity.Language

@Repository
interface LanguageRepository : JpaRepository<Language, Long> {
    fun findByCode(code: String): Language?
    fun existsByIsActiveTrue(): Boolean
    fun findByIsActiveTrue(): Language?

    @Modifying
    @Query("update Language language set language.isActive = :activationStatus where language.id = :id")
    fun switchActivationStatus(@Param("id") id: Long,
                         @Param("activationStatus") activationStatus: Boolean)
}