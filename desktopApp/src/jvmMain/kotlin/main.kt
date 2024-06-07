import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import navigation.NavigationHostComponent
import javax.swing.SwingUtilities


@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val windowState = WindowState(size = DpSize(480.dp, 840.dp))
    val lifecycle = LifecycleRegistry()
    val root = runOnMainThreadBlocking {  NavigationHostComponent(DefaultComponentContext(lifecycle)) }
    singleWindowApplication(
        state = windowState,
        title = "Planificateur",
        icon = BitmapPainter(useResource("logo.png", ::loadImageBitmap))
    ) {
        painterResource("logo.png")
        LifecycleController(lifecycle, windowState)

        App {
            root.render()
        }
    }
}

private inline fun <T : Any> runOnMainThreadBlocking(crossinline block: () -> T): T {
    lateinit var result: T
    SwingUtilities.invokeAndWait { result = block() }
    return result
}
