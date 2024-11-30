package com.example.aplikacjabit322.ui.screens.home

import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.example.aplikacjabit322.Bit322TopAppBar

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
) {
    Scaffold (
        topBar = {
            Bit322TopAppBar(
                title = "Witaj $login",
                canNavigateBack = true,
                navigateUp = navigateBack,
                canClickButton = true,
                onClickButton = {

                },
                buttonIcon = Icons.Outlined.AddCircle,
                onClickSecondButton = {

                },
                secondButtonIcon = Icons.Outlined.AddCircle
            )
        }
    ) {innerPadding->
        LazyColumn(content = {
            item {
                Text(text = "Artykuły")
                YouTubePlayer(
                    youtubeVideoId = "liJVSwOiiwg",
                    lifecycleOwner = LocalLifecycleOwner.current
                )


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