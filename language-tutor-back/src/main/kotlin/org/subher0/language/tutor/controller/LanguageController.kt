package org.subher0.language.tutor.controller

import io.swagger.annotations.ApiModelProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.subher0.language.tutor.dto.model.LanguageDto

@RestController
@RequestMapping(path = ["/language"])
class LanguageController {
    @ApiModelProperty(value = "Create a new language")
    @PostMapping
    fun createLanguage(body: LanguageDto): ResponseEntity<Void> {
        return ResponseEntity.ok(null as Void)
    }
}