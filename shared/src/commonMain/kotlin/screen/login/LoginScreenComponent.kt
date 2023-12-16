package screen.login

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.push
import navigation.Component
import navigation.NavHostComponent

class LoginScreenComponent(
    private val componentContext: ComponentContext,
    private val navigationService: StackNavigation<NavHostComponent.ScreenConfig>
) : Component {

    @Composable
    override fun render() {
        LoginScreen(this)
    }

    fun handleLoginClicked() {

        this.navigationService.push(NavHostComponent.ScreenConfig.Home)
    }
}