package org.subher0.language.tutor.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.subher0.language.tutor.dto.model.PartOfSpeechDto
import org.subher0.language.tutor.dto.response.EntityDto
import org.subher0.language.tutor.service.PartOfSpeechService

@RestController
@RequestMapping(path = ["/part-of-speech"])
@Api(tags = ["Parts of Speech"])
class PartOfSpeechController @Autowired constructor(
        private val partOfSpeechService: PartOfSpeechService
){
    @ApiOperation(value = "Create a new part of speech for active language")
    @PostMapping
    fun createPartOfSpeech(@RequestBody body: PartOfSpeechDto): ResponseEntity<EntityDto<PartOfSpeechDto>> {
        return partOfSpeechService.createPartOfSpeech(body)
    }
}