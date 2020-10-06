package br.com.sas.reativotop.publication.entrypoint

import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.service.PublicationService
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.json
import java.lang.RuntimeException

@RestController
class PublicationController(private val publicationService: PublicationService) {

    @GetMapping("/publications")
    suspend fun find(@RequestParam title: String): ResponseEntity<Any> {
        return when (val result = publicationService.findByTitle(title)) {
            is Ok -> ResponseEntity.ok(result.value)
            is Err -> { //
                when(result.error.message) {
                    "BadRequest" -> ResponseEntity.badRequest().build() //we can use application.yml messages
                    "NotFound" -> ResponseEntity.notFound().build()
                    else -> throw RuntimeException("")
                }

            }
        }
    }

}