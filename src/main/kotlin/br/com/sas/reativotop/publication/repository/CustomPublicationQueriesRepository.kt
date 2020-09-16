package br.com.sas.reativotop.publication.repository

import br.com.sas.reativotop.publication.model.Publication
import kotlinx.coroutines.flow.Flow

interface CustomPublicationQueriesRepository {

    suspend fun findByTitle(title: String): Flow<Publication>

}