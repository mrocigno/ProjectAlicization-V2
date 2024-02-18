package br.com.mrocigno.alicization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.mrocigno.alicization.data.model.Book
import br.com.mrocigno.alicization.data.repository.HomeRepository
import br.com.mrocigno.alicization.network.flow.MutableResponseFlow
import br.com.mrocigno.alicization.ui.theme.AlicizationTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val teste: HomeRepository by inject()
    val flow = MutableResponseFlow<List<Book>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlicizationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val response by flow.collectAsState()
                    Column {
                        when {
                            response.isEmpty -> Text("Vazio")
                            response.isLoading -> Text("Carregando...")
                            response.hasError -> response.error.message.orEmpty()
                            response.hasData -> {
                                val data = response.data.first()
                                AsyncImage(
                                    model = ImageRequest.Builder(this@MainActivity)
                                        .data(data.thumb)
                                        .setHeader("Referer", "https://www.mangatown.com/")
                                        .build(),
                                    contentDescription = "",
                                    modifier = Modifier.height(100.dp)
                                )
                                Text(text = data.name)
                            }
                            else -> ""
                        }
                        Button(onClick = { flow.sync(teste.getHome()) }) {
                            Text(text = "Botão")
                        }
                    }
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
    AlicizationTheme {
        Greeting("Android")
    }
}