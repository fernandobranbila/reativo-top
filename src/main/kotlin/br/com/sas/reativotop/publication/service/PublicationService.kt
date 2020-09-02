package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.publication.model.Activity
import br.com.sas.reativotop.publication.model.Meet
import br.com.sas.reativotop.publication.model.Publication
import kotlinx.coroutines.*
import org.springframework.stereotype.Component

@Component
class PublicationService {

    suspend fun save(publication: Publication): Publication {
        val publicationResult = savePublication(publication)
        val activity = activity(publicationResult.id!!, publicationResult.activity)
        return publicationResult.copy(activity = activity)
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
