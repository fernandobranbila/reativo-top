package br.com.sas.reativotop.publication.entrypoint

import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.service.PublicationService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class PublicationHandler(private val publicationService: PublicationService) {

    suspend fun getById(request: ServerRequest): ServerResponse =
            ServerResponse.ok().json().bodyValueAndAwait(request.pathVariable("publicationId"))


    suspend fun save(request: ServerRequest): ServerResponse {
        val body = request.awaitBody<Publication>()
        // paralelo
        val publication = GlobalScope.async {
            publicationService.save(body)
        }
        val findMeet = GlobalScope.async {
            publicationService.findMeetByTitlePublication(body.title)
        }

        return ServerResponse.created(URI.create("")).bodyValueAndAwait( Pair(publication.await(), findMeet.await()))
    }
}
