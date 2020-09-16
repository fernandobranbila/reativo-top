package br.com.sas.reativotop

import br.com.sas.reativotop.publication.entrypoint.PublicationHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routers {

    @Bean
    fun routes(publicationHandler: PublicationHandler) =
        coRouter {
            GET("/publications/{publicationId}", publicationHandler::getById)
            GET("/publications/")
            POST("/publications", publicationHandler::savePublication)
            //POST("/publications/{publicationId}/activities", publicationHandler::saveActivity)
        }

}