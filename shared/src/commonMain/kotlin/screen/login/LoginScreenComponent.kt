package screen.login

import androidx.compose.runtime.Composable
import apiclient.PlanificateurApiClient
import apiclient.PlanificateurRestApiClient
import apiclient.dtos.LoginRequest
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.ktor.http.*
import navigation.Component
import navigation.NavigationHostComponent

class LoginScreenComponent(
    private val componentContext: ComponentContext,
    private val navigationService: StackNavigation<NavigationHostComponent.ScreenConfig>
) : Component {

    private val _state: MutableValue<State> = MutableValue(State())
    private val settings = Settings()

    val state: Value<State> = _state;

    @Composable
    override fun render() {
        LoginScreen(this)
    }

    suspend fun handleLoginClicked() {
        val client: PlanificateurApiClient = PlanificateurRestApiClient(state.value.serverUrl)
        val response = client.login(LoginRequest(state.value.login, state.value.password))
        settings["token"] = response.token
        settings["serverUrl"] = state.value.serverUrl
        
        this.navigationService.push(NavigationHostComponent.ScreenConfig.Home)
    }

    fun updateLogin(newLogin: String){
        _state.update { it.copy(login = newLogin, password = it.password, serverUrl = it.serverUrl) }
    }

    fun updatePassword(newPassword: String){
        _state.update { it.copy(login = it.login, password = newPassword, serverUrl = it.serverUrl) }
    }
    
    fun updateServerUrl(newServerUrl: String) {
        _state.update { it.copy(login = it.login, password = it.password, serverUrl = newServerUrl) }
    }

    data class State(
        val login: String = "",
        val password: String = "",
        val serverUrl: String = ""
    ) {
        val isLoginEnabled: Boolean
            get() = !(login.isBlank() || password.isBlank() || serverUrl.isBlank())
    }
}