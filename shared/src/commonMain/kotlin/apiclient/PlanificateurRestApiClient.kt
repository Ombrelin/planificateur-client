package apiclient

import apiclient.dtos.LoginRequest
import apiclient.dtos.LoginResponse
import apiclient.dtos.PollWithoutVotes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class PlanificateurRestApiClient(private val serverUrl: String) : PlanificateurApiClient {

    private val client: HttpClient = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }

    override suspend fun login(request: LoginRequest): LoginResponse {
        val httpResponse: HttpResponse = client.post("$serverUrl/api/authentication/login"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        if (httpResponse.status.value !in 200..299) {
            throw RuntimeException("HTTP ${httpResponse.status.value} code ${httpResponse.status.description}")
        }
        
        return httpResponse.body();
    }

    override suspend fun getPolls(token: String): List<PollWithoutVotes> {
        val httpResponse: HttpResponse = client.get("$serverUrl/api/polls"){
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        };
        if (httpResponse.status.value !in 200..299) {
            throw RuntimeException("HTTP ${httpResponse.status.value} code ${httpResponse.status.description}")
        }

        return httpResponse.body();
    }
}