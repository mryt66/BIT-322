package com.example.aplikacjabit322.ui.screens.hobby


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplikacjabit322.R
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

object HobbyDestination {
    const val route = "hobby/{login}"
    const val arg = "login"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyScreen(
    login: String?,
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToHobby: (String) -> Unit,
    navigateToUpload: (String) -> Unit,
    navigateToMap: () -> Unit,
    viewModel: HobbyViewModel = viewModel(factory = AppViewModelProvider.Factory),
//    modifier: Modifier = Modifier.height(240.dp)
) {
    val hobbyUiState = viewModel.hobbyUiState

    Scaffold(
        topBar = {
            TopAppBarHobby(
                navigateBack = navigateBack,
                navigateToHome = { navigateToHome(login ?: "null") },
                navigateToProfile = { navigateToProfile(login ?: "null") },
                navigateToSearch = { navigateToHobby(login ?: "null") },
                navigateToUpload = { navigateToUpload(login ?: "null") },
                isRow = true
            )
        }, modifier = Modifier.fillMaxWidth()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            EntryDrop(
                types = hobbyUiState.hobbies,
                value = hobbyUiState.selectedHobby,
                onValueChange = { viewModel.updateSelectedHobby(it)},
                label = "Select hobby"
            )
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Button(onClick = { viewModel.changeList(0) }, modifier = Modifier.padding(8.dp)) {
                Text(text = "YT")
            }
                Button(onClick = { viewModel.changeList(1) }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Articles")
                }
                Button(onClick = { viewModel.changeList(2) }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Community")
                }
//                Button(onClick = { viewModel.changeList(3) }, modifier = Modifier.padding(8.dp)) {
//                    Text(text = "Map")
//                }


            }


        LazyColumn(
            content = {
                item {
//                when (hobbyUiState) {
//                    is HobbyUiState.Loading -> {
//                        Text(text = "Loading...", modifier = Modifier.padding(8.dp), fontSize = 32.sp)
//                    }
//                    is HobbyUiState.Error -> {
//                        Text(text = "Error", modifier = Modifier.padding(8.dp), fontSize = 32.sp)
//                    }
//                    is HobbyUiState.Success -> {
//                        val hobbies = (hobbyUiState as HobbyUiState.Success).hobbies
//                        hobbies.forEach { hobby ->
//                            HobbyItem(hobby = hobby)
//                        }
//                    }
//                }
                    when (hobbyUiState.whichList) {
                        0 -> {
                            val listOfVideos = hobbyUiState.listOfVideos
                            listOfVideos.forEach { link ->
                                YouTubePlayer(
                                    youtubeVideoId = link,
                                    lifecycleOwner = LocalLifecycleOwner.current
                                )
                            }
                        }

                        1 -> {
                            val listOfArticles = hobbyUiState.listOfArticles
                            listOfArticles.forEach { article ->
                                Column {
                                    Text(text = article, modifier = Modifier.padding(8.dp))
                                }
                            }

                        }
                        2 -> {
                            val listOfCommunities = hobbyUiState.listOfCommunities
                            listOfCommunities.forEach { community ->
                                Column(modifier = Modifier.padding(8.dp)) {

                                    AsyncImage(
                                        model = community.photoUrl,
                                        placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                                        error = painterResource(id = R.drawable.ic_launcher_foreground),
                                        contentDescription = "Zdjęcie społeczności",
                                    )
                                    Text(
                                        text = community.description,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = "@${community.nick}",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = community.title,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = "Likes: ${community.likes}",
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }
                        3 -> {
                            navigateToMap()
                        }
                    }
//                        YouTubePlayer(
//                            youtubeVideoId = "liJVSwOiiwg",
//                            lifecycleOwner = LocalLifecycleOwner.current
//                        )



                }
            }
        )
        }

    }
}


@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryDrop(
    types: List<String> = emptyList(),
    typesAndColor: List<Pair<String, Color>> = emptyList(),
    value: String,
    color: Color = Color.Black,
    onValueChange: (String) -> Unit,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    Row {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
            OutlinedTextField(
                value = value,
                label = { Text(label) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = color,
                    unfocusedTextColor = color,
                ),
                readOnly = true,
                onValueChange = {},
                modifier = Modifier
                    .menuAnchor()
                    .padding(8.dp)
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                typesAndColor.forEach { (type, color) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Image(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color)
                        )
                        DropdownMenuItem(text = { Text(text = type, color = color) }, onClick = {
                            onValueChange(type)
                            expanded = false
                        })
                    }
                }
                for (type in types) {
                    DropdownMenuItem(text = { Text(text = type) }, onClick = {
                        onValueChange(type)
                        expanded = false
                    })
                }
            }
        }
    }
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopAppBarHobby(
//    navigateBack: () -> Unit,
//    navigateToHome:(String) -> Unit,
//    navigateToProfile: (String) -> Unit,
//    navigateToSearch: (String) -> Unit,
//    navigateToUpload: (String) -> Unit,
//    isRow: Boolean
//) {
//    CenterAlignedTopAppBar(
//        title = {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxWidth()
//
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    IconButton(onClick = { navigateBack()}) {
//                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
//                    }
//                    IconButton(onClick = { navigateToUpload("null") }) {
//                        Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
//                    }
//                    IconButton(onClick = { navigateToHome("null") }) {
//                        Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
//                    }
//                    IconButton(onClick = { navigateToSearch("null")}) {
//                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
//                    }
//                    IconButton(onClick = { navigateToProfile("null")}) {
//                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "")
//                    }
//                }
//                if (isRow){
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Button(onClick = { /*TODO*/ }) {
//                            Text(text = "YT")
//                        }
//                        Button(onClick = { /*TODO*/ }) {
//                            Text(text = "Articles")
//                        }
//                        Button(onClick = { /*TODO*/ }) {
//                            Text(text = "Community")
//                        }
//                    }
//
//                }
//            }
//
//        },
//        Modifier.fillMaxWidth()
//    )
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHobby(
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToSearch: (String) -> Unit,
    navigateToUpload: (String) -> Unit,
    isRow: Boolean
) {
    CenterAlignedTopAppBar(
        title = {

                // Główna ikony nawigacji
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp) // Odstęp między rzędami
                ) {


                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "")
                    }
                    IconButton(onClick = { navigateToUpload("null") }) {
                        Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = "")
                    }
                    IconButton(onClick = { navigateToHome("null") }) {
                        Icon(imageVector = Icons.Outlined.Home, contentDescription = "")
                    }
                    IconButton(onClick = { navigateToSearch("null") }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                    }
                    IconButton(onClick = { navigateToProfile("null") }) {
                        Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "")
                    }
                }

                // Dodatkowy wiersz przy aktywnym `isRow`

        },
        modifier = Modifier
            .fillMaxWidth(),

//            .height(if (isRow) 240.dp else 60.dp) // Zwiększ wysokość, jeśli dodatkowy wiersz jest aktywny
    )
}


