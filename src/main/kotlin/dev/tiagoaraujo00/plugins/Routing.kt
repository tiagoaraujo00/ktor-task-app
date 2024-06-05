package dev.tiagoaraujo00.plugins

import dev.tiagoaraujo00.Routes.taskRouting
import dev.tiagoaraujo00.models.Priority
import dev.tiagoaraujo00.models.Task
import dev.tiagoaraujo00.models.tasksAsTable
import dev.tiagoaraujo00.repositories.TaskRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        staticResources("/task-ui", "task-ui")
        taskRouting()
    }
}
