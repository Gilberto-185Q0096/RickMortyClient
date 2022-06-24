package edu.itsco.rickmortyclient.ui.home

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.itsco.rickmortyclient.data.api.model.Character

@Composable
fun HomeScreen(){

    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by homeViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("Rick and Morty")}
            )
        }
    ){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.surface
        ){
            LazyColumn{
                if(state.isEmpty()){
                    item{
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                items(items = state){ character: Character ->
                    CardCharacter(character = character)
                }
            }
        }
    }
}

@Composable
fun CardCharacter(character: Character){
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
        ){
            Surface(
                modifier = Modifier.size(100.dp),
                shape = RectangleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.25f)
            ){
                AsyncImage(
                    model = character.image,
                    contentDescription = "Imagen RickMortyApi",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth()
            ){
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h5
                )
                Divider()
                Text(
                    text = "${character.status} - ${character.species}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = character.origin.name,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}