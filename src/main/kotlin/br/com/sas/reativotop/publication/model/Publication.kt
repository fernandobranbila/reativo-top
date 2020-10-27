package br.com.sas.reativotop.publication.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("publication")
data class Publication(@Id val id: Long? = null,
                       val title: String) {
}
