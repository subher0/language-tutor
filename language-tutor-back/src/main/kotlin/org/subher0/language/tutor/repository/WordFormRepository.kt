package org.subher0.language.tutor.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.subher0.language.tutor.entity.WordForm

@Repository
interface WordFormRepository : JpaRepository<WordForm, Long> {
}