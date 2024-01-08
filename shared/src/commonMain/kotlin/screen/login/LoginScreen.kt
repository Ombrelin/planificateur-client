package screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun LoginScreen(component: LoginScreenComponent) {

    val state by component.state.subscribeAsState()

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(contentAlignment = Alignment.TopCenter) {
                Row(
                    modifier = Modifier.padding(top = 80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Planificateur", style = MaterialTheme.typography.h4, textAlign = TextAlign.Center)
                }
            }

            TextField(
                value = state.login,
                onValueChange = component::updateLogin,
                label = { Text("Login") }
            )
            TextField(
                value = state.password,
                onValueChange = component::updatePassword,
                label = { Text("Password") }
            )
            Button(onClick = component::handleLoginClicked, enabled = state.isLoginEnabled) {
                Text("Login")
            }

        }
    }


}
