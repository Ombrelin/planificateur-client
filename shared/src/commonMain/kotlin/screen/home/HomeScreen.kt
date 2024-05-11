package screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    val state by component.state.subscribeAsState()
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        component.loadPolls()
    }

    Surface {
        Column {
            Box(contentAlignment = Alignment.TopCenter) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Polls", style = MaterialTheme.typography.h4, textAlign = TextAlign.Center)
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                state.polls.forEach { poll ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = poll.id, style = MaterialTheme.typography.h5, textAlign = TextAlign.Left)
                    }
                }
            }
        }
    }

}