package br.com.sas.reativotop.publication.entrypoint

import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.service.PublicationService
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class PublicationHandler(private val publicationService: PublicationService) {

    suspend fun getById(request: ServerRequest): ServerResponse =
            ServerResponse.ok().json().bodyValueAndAwait(request.pathVariable("publicationId"))


    suspend fun save(request: ServerRequest): ServerResponse {
        val body = request.awaitBody<Publication>()
        val publication = publicationService.save(body)
        return ServerResponse.created(URI.create("")).bodyValueAndAwait(publication)
    }
}