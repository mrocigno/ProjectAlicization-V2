package br.com.mrocigno.alicization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.mrocigno.alicization.data.repository.HomeRepository
import br.com.mrocigno.alicization.network.flow.MutableResponseFlow
import br.com.mrocigno.alicization.ui.theme.AlicizationTheme
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.http.GET

class MainActivity : ComponentActivity() {

    val teste: HomeRepository by inject()
    val flow = MutableResponseFlow<String>()

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
                        Text(
                            when {
                                response.isEmpty -> "Vazio"
                                response.isLoading -> "Carregando..."
                                response.data != null -> response.data.orEmpty()
                                response.error != null -> response.error?.message.orEmpty()
                                else -> ""
                            }
                        )
                        Button(onClick = { flow.sync(teste.getHome()) }) {
                            Text(text = "Botão")
                        }
                    }
                }
            }
        }
    }
}

interface Teste {

    @GET("https://www.mangatown.com/")
    fun get(): Call<String>

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