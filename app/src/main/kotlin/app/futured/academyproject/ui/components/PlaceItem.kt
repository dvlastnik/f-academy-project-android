package app.futured.academyproject.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.futured.academyproject.data.model.local.Place
import coil.compose.AsyncImage

@Composable
fun PlaceItem(
    place: Place,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Column() {
            Card(modifier = Modifier.size(70.dp)) {
                AsyncImage(model = place.image1Url, contentDescription = null, modifier = Modifier.size(80.dp), contentScale = ContentScale.FillBounds)
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 10.dp)
        ) {
            Row(modifier = Modifier.padding(5.dp)) {
                Text(text = place.name, fontWeight = FontWeight.Bold)
            }
            Row(modifier = Modifier.padding(5.dp)) {
                Text(text = place.type, fontSize = 14.sp)
            }
        }
    }
}