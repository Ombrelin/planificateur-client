package screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(component: LoginScreenComponent) {

    val state by component.state.subscribeAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(content = {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {

            Image(painter = painterResource("logo.png"), contentDescription = "Application Logo")

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.login,
                onValueChange = component::updateLogin,
                label = { Text("Login") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = component::updatePassword,
                label = { Text("Password") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.serverUrl,
                onValueChange = component::updateServerUrl,
                label = { Text("Server URL") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        component.handleLoginClicked()
                    }
                }, enabled = state.isLoginEnabled
            ) {
                Text("Login")
            }
        }
    })
}