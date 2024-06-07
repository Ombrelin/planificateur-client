import androidx.compose.runtime.Composable
import com.example.compose.AppTheme

@Composable
fun App(content: @Composable () -> Unit) {
    AppTheme(
        darkTheme = true,
        content = content
    )
}

expect fun getPlatformName(): String