package br.com.sas.reativotop.publication.model

import org.springframework.data.relational.core.mapping.Table

@Table("publication")
data class Publication(val id: Long? = null,
                       val title: String) {
}
