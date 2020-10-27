package br.com.sas.reativotop.publication.entrypoint

import br.com.sas.reativotop.orThrow
import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.service.ActivityService
import br.com.sas.reativotop.publication.service.PublicationService
import kotlinx.coroutines.*
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI


@Component
class PublicationHandler(private val publicationService: PublicationService,
                         private val activityService: ActivityService) {

    suspend fun getById(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().json().bodyValueAndAwait(request.pathVariable("publicationId"))
    }

    suspend fun getByTitle(request: ServerRequest): ServerResponse {
        val result = coroutineScope {
            publicationService.findByTitle(request.queryParam("title").get())
        }
        return ServerResponse.ok().json().bodyAndAwait(result.orThrow())
    }


    suspend fun savePublication(request: ServerRequest): ServerResponse {
        coroutineScope {
            val body = request.awaitBody<Publication>()
            async {
                publicationService.save(body)
            }
        }
        return ServerResponse.noContent().buildAndAwait()
    }

/*
    suspend fun saveActivity(request: ServerRequest): ServerResponse {

    }*/


}
