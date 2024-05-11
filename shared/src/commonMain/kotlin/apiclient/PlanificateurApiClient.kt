package apiclient

import apiclient.dtos.LoginRequest
import apiclient.dtos.LoginResponse
import apiclient.dtos.PollWithoutVotes

interface PlanificateurApiClient {
    
    suspend fun login(request: LoginRequest): LoginResponse
    suspend fun getPolls(token: String): List<PollWithoutVotes>
}