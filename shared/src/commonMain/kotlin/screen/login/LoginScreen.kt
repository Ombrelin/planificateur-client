package screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

@Composable
fun LoginScreen(component: LoginScreenComponent) {

    val state by component.state.subscribeAsState()

    Column {
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