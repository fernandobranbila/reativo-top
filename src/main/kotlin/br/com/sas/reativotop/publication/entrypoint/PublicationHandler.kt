package br.com.sas.reativotop.publication.entrypoint

import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.service.ActivityService
import br.com.sas.reativotop.publication.service.PublicationService
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI


@Component
class PublicationHandler(private val publicationService: PublicationService,
                         private val activityService: ActivityService) {

    suspend fun getById(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().json().bodyValueAndAwait(request.pathVariable("publicationId"))
    }

/*    suspend fun getByTitle(request: ServerRequest): ServerResponse {
        return when (val result = publicationService.findByTitle(request.queryParam("title").get())) {
            is Ok -> ServerResponse.ok().json().bodyAndAwait(result.value)
            is Err -> ServerResponse.badRequest().build()

        }
    }*/


    suspend fun savePublication(request: ServerRequest): ServerResponse {
        val body = request.awaitBody<Publication>()
        val publication = GlobalScope.async {
            publicationService.save(body)
        }
        return ServerResponse.created(URI.create("")).bodyValueAndAwait(publication.await())
    }


/*
    suspend fun saveActivity(request: ServerRequest): ServerResponse {

    }*/


}
