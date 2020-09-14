package br.com.sas.reativotop.publication.service

import br.com.sas.reativotop.publication.model.Activity
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.into
import org.springframework.stereotype.Component

@Component
class ActivityService (private val dataBaseClient: DatabaseClient) {

    suspend fun saveActivity(activity: Activity) =
            dataBaseClient.insert()
                    .into<Activity>()
                    .table("activity")

}
