@file:Suppress("unused")

package org.akrck02.countless.data.exception

enum class ErrorCode(
    val code: Int,
    val message: String
) {
    // 0 - 100 System error
    UNKNOWN(0, "Unknown Exception."),

    // 101 - 200  Validation error
    MUST_NOT_BE_NULL(101, "%s must not be null."),
    MUST_BE_POSITIVE(102, "%s must be positive."),
    MUST_NOT_BE_BLANK(103, "%s must not be blank."),

}