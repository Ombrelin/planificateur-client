import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

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