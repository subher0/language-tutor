package org.subher0.language.tutor.dto.response

data class CountableDto<T>(val data: List<T>? = null,
                           val totalElements: Long? = null,
                           val errorInfo: ErrorInfo? = null)