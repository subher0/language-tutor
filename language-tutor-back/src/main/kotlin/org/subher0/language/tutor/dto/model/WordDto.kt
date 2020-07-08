package org.subher0.language.tutor.dto.model

import java.time.ZonedDateTime

data class WordDto(val id: Long?,
                   val word: String?,
                   val description: String?,
                   val createTime: ZonedDateTime?,
                   val wordForm: WordFormDto?,
                   val partOfSpeechDto: PartOfSpeechDto?,
                   val otherForms: List<WordDto>?,
                   val numberOfViews: Long?,
                   val attachments: AttachmentDto?

)