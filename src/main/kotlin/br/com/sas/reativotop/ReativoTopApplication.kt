package br.com.sas.reativotop

import io.r2dbc.spi.ConnectionFactories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.core.DatabaseClient


@SpringBootApplication
class ReativoTopApplication

fun main(args: Array<String>) {
    runApplication<ReativoTopApplication>(*args)

    val connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")

    val client = DatabaseClient.create(connectionFactory)

    client.execute("CREATE TABLE publication (id VARCHAR(255) PRIMARY KEY, title VARCHAR(255));")
            .then()
    client.execute("CREATE TABLE activity (id VARCHAR(255) PRIMARY KEY, publicationId INTEGER, name VARCHAR(255));")
            .then()
}
