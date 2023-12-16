package fr.arsenelapostolet.planificateur

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import navigation.NavigationHostComponent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = NavigationHostComponent(componentContext = defaultComponentContext())

        setContent {
            root.render()
        }
    }

}