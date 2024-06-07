package screen.home

import androidx.compose.runtime.Composable
import apiclient.PlanificateurApiClient
import apiclient.PlanificateurRestApiClient
import apiclient.dtos.PollWithoutVotes
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import navigation.Component
import navigation.NavigationHostComponent
import screen.login.LoginScreenComponent.State

class HomeScreenComponent(
    private val componentContext: ComponentContext,
    private val navigationService: StackNavigation<NavigationHostComponent.ScreenConfig>
) : Component {
    private val settings = Settings()
    private val _state: MutableValue<State> = MutableValue(State(listOf()))
    val state: Value<State> = _state;

    suspend fun loadPolls() {
        val token: String? = settings["token"];
        val serverUrl: String? = settings["serverUrl"];

        try {
            if (token == null || serverUrl == null) {
                this.navigationService.replaceAll(NavigationHostComponent.ScreenConfig.Login)
            } else {
                val client: PlanificateurApiClient = PlanificateurRestApiClient(serverUrl);
                val polls = client.getPolls(token);
                _state.update { State(polls) }
            }
        } catch (e: Exception) {
            this.navigationService.replaceAll(NavigationHostComponent.ScreenConfig.Login)
        }
 
    }

    @Composable
    override fun render() {
        HomeScreen(this)
    }

    fun logout() {
        settings.remove("token")
        this.navigationService.replaceAll(NavigationHostComponent.ScreenConfig.Login)
    }

    data class State(
        val polls: List<PollWithoutVotes>
    )
}