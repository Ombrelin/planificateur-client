package screen.home

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import navigation.Component
import navigation.NavigationHostComponent

class HomeScreenComponent(
    private val componentContext: ComponentContext,
    private val navigation: StackNavigation<NavigationHostComponent.ScreenConfig>
) : Component {

    @Composable
    override fun render() {
        HomeScreen(this)
    }
}