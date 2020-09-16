package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.publication.model.Activity
import br.com.sas.reativotop.publication.model.Meet
import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component

@Component
class PublicationService (private val publicationRepository: PublicationRepository){

    suspend fun save(publication: Publication): Publication {
        return publicationRepository.save(publication).awaitFirst()
    }

    suspend fun findByTitle(title: String): Flow<Publication> =
            publicationRepository.findByTitle(title)

    private suspend fun activity(publicationId: Long, activity: Activity): Activity {
        return activity.copy(id = 1)
    }


    private suspend fun savePublication(publication: Publication): Publication {
        return publication.copy(id = 1)
    }

     suspend fun findMeetByTitlePublication(title: String): Meet {
        return Meet(1, "http://batata")
    }

}
