package screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(component: HomeScreenComponent) {
    var menuExpanded by remember { mutableStateOf(false) }
    val state by component.state.subscribeAsState()
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        component.loadPolls()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Polls", style = TextStyle(color = Color.White, fontSize = 24.sp))
            },
            actions = {
                IconButton(
                    onClick = {
                        menuExpanded = true
                    }
                ) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text("Log Out", color = Color.White)
                        },
                        onClick = {
                            coroutineScope.launch {
                                component.logout();
                            }
                        }
                    )
                }
            }
        )
    }, floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, "")
        }
    }, content = {
        Column {
            Column(modifier = Modifier.fillMaxSize()) {
                state.polls.forEach { poll ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = poll.id, style = MaterialTheme.typography.h5, textAlign = TextAlign.Left)
                    }
                }
            }
        }
    })

}