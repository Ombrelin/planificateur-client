package screen.login

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(component: LoginScreenComponent){
    Button(onClick = component::handleLoginClicked){
        Text("Login")
    }
}