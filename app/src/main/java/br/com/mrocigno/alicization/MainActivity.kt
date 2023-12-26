package br.com.mrocigno.alicization

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.mrocigno.alicization.data.repository.HomeRepository
import br.com.mrocigno.alicization.network.Network
import br.com.mrocigno.alicization.ui.theme.AlicizationTheme
import org.jsoup.Jsoup
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class MainActivity : ComponentActivity() {

    val teste: HomeRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlicizationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Button(onClick = {


                        /*do things*/
                        Network.retrofit.create(Teste::class.java).get().enqueue(object : Callback<String> {
                            override fun onResponse(
                                call: Call<String>,
                                response: Response<String>
                            ) {
                                val teste = Jsoup.parse(response.body()!!)
                                val i = 1
                            }

                            override fun onFailure(call: Call<String>, t: Throwable) {
                                val error = t
                            }
                        })
                    }) {
                        Text(teste.getHome())
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