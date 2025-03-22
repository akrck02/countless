package org.akrck02.countless.exception

class ServiceException(
    val code: ErrorCode = ErrorCode.UNKNOWN,
    message: String = ErrorCode.UNKNOWN.message,
    override val cause: Throwable? = null
) : Exception(message, cause)