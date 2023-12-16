package screen.login

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import navigation.Component
import navigation.NavigationHostComponent

class LoginScreenComponent(
    private val componentContext: ComponentContext,
    private val navigationService: StackNavigation<NavigationHostComponent.ScreenConfig>
) : Component {

    private val _state: MutableValue<State> = MutableValue(State())
    val state: Value<State> = _state;

    @Composable
    override fun render() {
        LoginScreen(this)
    }

    fun handleLoginClicked() {
        this.navigationService.push(NavigationHostComponent.ScreenConfig.Home)
    }

    fun updateLogin(newLogin: String){
        _state.update { it.copy(login = newLogin, password = it.password) }
    }

    fun updatePassword(newPassword: String){
        _state.update { it.copy(login = it.login, password = newPassword) }
    }

    data class State(
        val login: String = "",
        val password: String = ""
    ) {
        val isLoginEnabled: Boolean
            get() = !(login.isBlank() || password.isBlank())
    }
}