package screen.home

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import navigation.Component
import navigation.NavHostComponent

class HomeScreenComponent(
    private val componentContext: ComponentContext,
    private val navigation: StackNavigation<NavHostComponent.ScreenConfig>
) : Component {

    @Composable
    override fun render() {
        HomeScreen(this)
    }
}