package dev.tiagoaraujo00

import dev.tiagoaraujo00.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }

    @Test
    fun taskCanBeFoundByPriority() = testApplication{
        application {
            configureRouting()
        }
        client.get("/task/byPriority/Medium").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertContains(bodyAsText(), "Mow the lawn")
            assertContains(bodyAsText(), "Paint the fence")
        }
    }
}
