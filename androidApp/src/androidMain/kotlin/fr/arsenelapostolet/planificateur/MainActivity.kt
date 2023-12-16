package fr.arsenelapostolet.planificateur

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.defaultComponentContext
import navigation.NavHostComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = NavHostComponent(componentContext = defaultComponentContext())

        setContent {
            root.render()
        }
    }

}