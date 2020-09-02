package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.publication.model.Publication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component

@Component
class PublicationService {

    suspend fun save(publication: Publication): Publication =
            withContext(Dispatchers.Default) {
                publication.copy(id = 1)
            }
}