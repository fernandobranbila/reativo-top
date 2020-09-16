package br.com.sas.reativotop.publication.repository

import br.com.sas.reativotop.publication.model.Publication
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface PublicationRepository : ReactiveCrudRepository<Publication, Integer>, CustomPublicationQueriesRepository {
}