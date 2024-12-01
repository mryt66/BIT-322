package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.aplikacjabit322.R
import com.example.aplikacjabit322.ui.AppViewModelProvider
import com.example.aplikacjabit322.ui.screens.hobby.TopAppBarHobby

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

object HomeDestination {
    const val route = "home/{login}"
    const val arg = "login"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    login: String?,
    navigateBack: () -> Unit,
    navigateToHome: (String) -> Unit,
    navigateToProfile: (String) -> Unit,
    navigateToHobby: (String) -> Unit,
    navigateToUpload:( String) -> Unit,


    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState = viewModel.homeUiState

    Scaffold (
        topBar = {
            TopAppBarHobby(
                navigateBack = navigateBack,
                navigateToHome = navigateToHome,
                navigateToProfile = navigateToProfile ,
                navigateToSearch = navigateToHobby,
                navigateToUpload = navigateToUpload,
                isRow = false
            )
        }
    ) {innerPadding->
        LazyColumn(content = {
            item {
                Text(text = "Witaj $login", modifier = Modifier.padding(8.dp), fontSize = 32.sp)
//                YouTubePlayer(
//                    youtubeVideoId = "liJVSwOiiwg",
//                    lifecycleOwner = LocalLifecycleOwner.current
//                )
                AsyncImage(
                    model = "https://delasign.com/delasignBlack.png",
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    error = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "The delasign logo",
                )
                Text(text = "Jakaś treść Artykułu", modifier = Modifier.padding(8.dp))


            }
        },
            modifier = Modifier.padding(innerPadding)
        )
    }


}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
){
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { context->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                    }
                })
            }
        }
    )
}