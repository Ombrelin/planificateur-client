package navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.russhwolf.settings.Settings
import org.kodein.di.*
import screen.home.HomeScreenComponent
import screen.login.LoginScreenComponent

class NavigationHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<ScreenConfig>()
    private val settings = Settings()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = if(settings.hasKey("token")) ScreenConfig.Home else ScreenConfig.Login,
        childFactory = ::createScreenComponent
    )

    private var di: DI? = null;

    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component {
        if (di == null){
           di = DI {
                bindSingleton<StackNavigation<ScreenConfig>> { navigation }
            }
        }

        return when (screenConfig) {
            is ScreenConfig.Home -> di!!.direct.newInstance { HomeScreenComponent(componentContext, instance()) }
            is ScreenConfig.Login -> di!!.direct.newInstance { LoginScreenComponent(componentContext, instance()) }
        }
    }

    @Composable
    override fun render() {
        Children(
            stack = stack,
            animation = stackAnimation(fade() + scale()),
        ) {
            it.instance.render()
        }
    }


    sealed class ScreenConfig : Parcelable {
        @Parcelize
        data object Login : ScreenConfig()

        @Parcelize
        data object Home : ScreenConfig()
    }
}

