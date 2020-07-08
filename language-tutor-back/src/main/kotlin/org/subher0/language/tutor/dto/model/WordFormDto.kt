package org.subher0.language.tutor.dto.model

import org.subher0.language.tutor.dto.model.PartOfSpeechDto

data class WordFormDto(val id: Long?,
                       val name: String?,
                       val description: String?,
                       val isRoot: Boolean?,
                       val language: PartOfSpeechDto?)