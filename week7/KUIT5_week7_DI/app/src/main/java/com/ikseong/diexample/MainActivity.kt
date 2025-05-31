package com.ikseong.diexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikseong.diexample.ui.github.viewmodel.GithubViewModel
import com.ikseong.diexample.ui.theme.DIExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DIExampleTheme{
                val context = LocalContext.current.applicationContext as App
                val viewModel: GithubViewModel = viewModel(
                    factory = AppContainer.GithubViewModelFactory(context.appContainer.provideGithubRepository())
                )

                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                //val uiState2 by viewModel.uiState.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = uiState.name,
                        modifier = Modifier.padding(innerPadding)
                    )
                    Greeting(
                        name = uiState.githubUrl,
                        modifier = Modifier.padding(innerPadding)
                    )
                    Greeting(
                        name = uiState.id.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DIExampleTheme {
        Greeting("Android")
    }
}