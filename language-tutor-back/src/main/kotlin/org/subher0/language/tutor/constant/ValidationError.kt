package org.subher0.language.tutor.constant

import org.subher0.language.tutor.dto.response.ErrorInfo

class ValidationError(private val code: String,
                      private val message: String) {
    companion object {
        val LANGUAGE_UNIQUE_CONSTRAINT_VIOLATION
                = ValidationError("0.000", "language_already_exists")
        val LANGUAGE_UNIQUE_ACTIVE_VIOLATION
                = ValidationError("0.001", "only_one_language_can_be_active")
        val NO_ACTIVE_LANGUAGE = ValidationError("0003", "no_active_language")

        val PART_OF_SPEECH_UNIQUE_CONSTRAINT_VIOLATION
                = ValidationError("1.000", "part_of_speech_with_name_exists_for_language")
    }

    fun toErrorInfo() = ErrorInfo(code, message)
}