package org.subher0.language.tutor.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.subher0.language.tutor.dto.model.LanguageDto
import org.subher0.language.tutor.dto.response.CountableDto
import org.subher0.language.tutor.dto.response.EmptyResponse
import org.subher0.language.tutor.dto.response.EntityDto
import org.subher0.language.tutor.service.LanguageService

@RestController
@RequestMapping(path = ["/languages"])
@Api(tags = ["Languages"])
class LanguageController @Autowired constructor(
        private val languageService: LanguageService
){
    @ApiOperation(value = "Create a new language")
    @PostMapping
    fun createLanguage(@RequestBody body: LanguageDto): ResponseEntity<EntityDto<LanguageDto>> {
        return languageService.createLanguage(body)
    }

    @ApiOperation(value = "List all languages")
    @GetMapping
    fun listLanguages(): ResponseEntity<CountableDto<LanguageDto>> {
        return languageService.listLanguages()
    }

    @ApiOperation(value = "Activate language")
    @PutMapping(path = ["/{id}/activate"])
    fun activateLanguage(@PathVariable(name = "id") id: Long): ResponseEntity<EmptyResponse> {
        return languageService.activateLanguage(id)
    }

    @ApiOperation(value = "Get active language")
    @GetMapping(path = ["/active"])
    fun getActiveLanguage(): ResponseEntity<EntityDto<LanguageDto>> {
        return languageService.getActiveLanguage()
    }
}