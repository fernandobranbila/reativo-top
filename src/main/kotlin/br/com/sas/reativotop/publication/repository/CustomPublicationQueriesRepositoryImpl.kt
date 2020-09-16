package br.com.sas.reativotop.publication.repository

import br.com.sas.reativotop.publication.model.Publication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.flow
import org.springframework.data.r2dbc.query.Criteria.where
import org.springframework.stereotype.Component

@Component
class CustomPublicationQueriesRepositoryImpl(
        private val databaseClient: DatabaseClient
) : CustomPublicationQueriesRepository {

    override suspend fun findByTitle(title: String): Flow<Publication> {

        databaseClient.select()
                .from(Publication::class.java)
                .matching(where("title").like(title))
                .fetch()
                .all()

        return databaseClient
                .execute("SELECT * FROM publication WHERE title LIKE :title")
                .bind("title", title)
                .`as`(Publication::class.java)
                .fetch()
                .flow()
    }
}