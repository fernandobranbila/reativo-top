package br.com.sas.reativotop.exception

import java.lang.RuntimeException

class ForbiddenException(message: String) : RuntimeException(message)