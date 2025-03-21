package org.akrck02.route

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.akrck02.exception.ServiceException

/**
 * Run code returning exceptions in JSON format
 * @param call The routing call
 * @param code The code to execute
 */
suspend fun runSecure(
    call: RoutingCall,
    code: suspend () -> Unit
) {
    try {
        code()
    } catch (e: ServiceException) {
        call.respond(
            status = HttpStatusCode(value = e.status, description = e.message),
            message = Json.encodeToString(e)
        )
    } catch (e: Exception) {
        call.respond(
            status = HttpStatusCode.InternalServerError,
            message = Json.encodeToString(
                ServiceException(
                    status = 500,
                    code = 0,
                    message = e.message.orEmpty()
                )
            )
        )
    }
}