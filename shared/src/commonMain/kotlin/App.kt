import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi

val Black = Color(0xFF000113)
val DarkTeal = Color(0XFF014D4E)


private val DarkColorScheme = darkColorScheme(
    primary = DarkTeal,
    surface = Black,
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColorScheme, content = content)
}

expect fun getPlatformName(): String