package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.publication.model.Activity
import br.com.sas.reativotop.publication.model.Meet
import br.com.sas.reativotop.publication.model.Publication
import br.com.sas.reativotop.publication.model.PublicationError
import br.com.sas.reativotop.publication.repository.PublicationRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

@Component
class PublicationService(private val publicationRepository: PublicationRepository) {

    suspend fun save(publication: Publication): Publication {
        return publicationRepository.save(publication).awaitFirst()
    }

/*    suspend fun findByTitle(title: String): Flow<Publication> {

            return publicationRepository.findByTitle(title)

    }*/

    suspend fun findByTitle(title: String): Result<Flow<Publication>, PublicationError> {
        if (title.length >= 6) { //batata
            val test = publicationRepository.findByTitle(title)
            return Ok(test)
        } else if (title.length >= 2 && title.length <= 4){
            return Err(PublicationError("BadRequest"))
        }
        else {
            return Err(PublicationError("NotFound"))
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

