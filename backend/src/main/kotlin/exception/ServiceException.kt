package org.akrck02.exception

class ServiceException(
    val status: Int = 500,
    val code: Int = 0,
    override val message: String = "Unexpected exception"
) : Exception()