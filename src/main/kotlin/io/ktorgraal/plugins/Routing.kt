package io.ktorgraal.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.text.SimpleDateFormat
import java.util.*

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            call.respondText("Hello GraalVM! at $currentDate")
            call.application.environment.log.info("Call made to /")
        }
    }

}
