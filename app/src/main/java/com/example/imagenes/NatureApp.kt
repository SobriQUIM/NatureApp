package com.example.imagenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NatureApp()
        }
    }
}

@Composable
fun NatureApp() {
    val scrollState = rememberScrollState()

    val places = listOf(
        Place("Río Amazonas", "El río Amazonas es un río de América del Sur que atraviesa Perú, Colombia y Brasil. Es el río más largo y caudaloso del mundo.", "https://historia.nationalgeographic.com.es/medio/2023/09/14/istock-1461314700_58141006_230914100817_1280x853.jpg"),
        Place("Gran Cañon", "El Gran Cañón de Arizona es una formación natural que se distingue por las bandas estratificadas de roca roja.", "https://www.lavanguardia.com/files/og_thumbnail/uploads/2019/09/26/5fa5338881ffa.jpeg"),
        Place("Desierto del Sahara", "El Sáhara o Sahara es el desierto cálido más grande del mundo y el tercero más grande después de la Antártida y el Ártico. Con más de 9 400 000 km² de superficie.", "https://www.ngenespanol.com/wp-content/uploads/2023/08/hace-miles-de-anos-el-desierto-del-sahara-era-un-bosque-seco.jpg"),
        Place("Monte Everest", "El monte Everest o Éverest es la montaña más alta de la superficie del planeta Tierra, con una altitud de 8848,86 metros sobre el nivel del mar.", "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/media/image/2020/12/china-nepal-llegan-acuerdo-fin-sabemos-altura-exacta-everest-2163191.jpg?tf=3840x"),
        Place("Gran Barrera de Coral", "La Gran Barrera de Coral, frente a la costa de Queensland en el noreste de Australia, es la especie viviente más grande de la Tierra, que es visible incluso desde el espacio exterior.", "https://media.traveler.es/photos/63ecf8d844c1a0e03615a59e/16:9/w_2560%2Cc_limit/2H0WB0B%2520(1).jpg")
    )
    places[0]
    print(places)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        places.forEach { place ->
            PlaceCard(place = place)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PlaceCard(place: Place) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = place.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = place.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

data class Place(val name: String, val description: String, val imageUrl: String)

@Preview(showBackground = true)
@Composable
fun NatureAppPreview() {
    NatureApp()
}
