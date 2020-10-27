package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.*
import br.com.sas.reativotop.exception.ForbiddenException
import br.com.sas.reativotop.exception.NotFoundException
import br.com.sas.reativotop.publication.model.Activity
import br.com.sas.reativotop.publication.model.Meet
import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Component

@Component
class PublicationService(private val publicationRepository: PublicationRepository) {

    suspend fun save(publication: Publication) {
        publicationRepository.save(publication).awaitFirst()
    }

/*    suspend fun findByTitle(title: String): Flow<Publication> {

            return publicationRepository.findByTitle(title)

    }*/

    suspend fun findByTitle(title: String): Result<Flow<Publication>, Exception> {
        if (title.length >= 6) {
            val test = publicationRepository.findByTitle(title)
            return Success(test)
        } else if (title.length >= 2 && title.length <= 4) {


            val a = test(title).onFailure { return it }

            return Success(publicationRepository.findByTitle(a))

        } else {
            return Failure(ForbiddenException(""))
        }
    }

    suspend fun test(title: String): Result<String, Exception> {
        if (title.length == 3) {
            return Failure(NotFoundException(""))
        } else {
            return Success(title)
        }

    }


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

