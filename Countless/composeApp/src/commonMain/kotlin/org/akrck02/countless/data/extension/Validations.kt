@file:Suppress("unused")

package org.akrck02.countless.data.extension

import org.akrck02.countless.data.model.exception.ErrorCode
import org.akrck02.countless.data.model.exception.ServiceException


fun Any?.assertNotNull(name: String = "value") {
    this ?: throw ServiceException(ErrorCode.MUST_NOT_BE_NULL, ErrorCode.MUST_NOT_BE_NULL.message.format(name))
}

fun Int?.assertPositive(name: String = "value") {
    this?.takeIf { 0 <= this } ?: throw ServiceException(ErrorCode.MUST_BE_POSITIVE, ErrorCode.MUST_BE_POSITIVE.message.format(name))
}

fun Double?.assertPositive(name: String = "value") {
    this?.takeIf { 0 <= this } ?: throw ServiceException(ErrorCode.MUST_BE_POSITIVE, ErrorCode.MUST_BE_POSITIVE.message.format(name))
}