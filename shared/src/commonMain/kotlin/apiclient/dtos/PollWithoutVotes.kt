package apiclient.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PollWithoutVotes(val id: String, val dates: List<String>, val expirationDate: String)
