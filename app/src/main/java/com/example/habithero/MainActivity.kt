package com.example.habithero

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.habithero.core.designsystem.HabitHeroTheme
import com.example.habithero.core.di.repositoryModule
import com.example.habithero.core.di.useCaseModule
import com.example.habithero.core.di.viewModelModule
import com.example.habithero.core.ui.NavigationRoot
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
        enableEdgeToEdge()
        setContent {
            HabitHeroTheme {
                NavigationRoot()
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitHeroTheme {
        NavigationRoot()
    }
}
