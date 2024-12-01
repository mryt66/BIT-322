package com.example.aplikacjabit322.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
        },

    ) {innerPadding->

        val maxItemsPerCategory = 5

        var limitedFunFacts = homeUiState.listOfFunFacts.take(maxItemsPerCategory)
        val limitedCommunities = homeUiState.listOfCommunities.take(maxItemsPerCategory)
        val limitedArticles = homeUiState.listOfArticles.take(maxItemsPerCategory)
        val limitedImages = homeUiState.listOfImages.take(maxItemsPerCategory)


        val mixedList = mutableListOf<Any>()
        for (i in 0 until maxItemsPerCategory) {
            if (i < limitedFunFacts.size) mixedList.add(limitedFunFacts[i])
            if (i < limitedCommunities.size) mixedList.add(limitedCommunities[i])
            if (i < limitedArticles.size) mixedList.add(limitedArticles[i])
            if (i < limitedImages.size) mixedList.add(limitedImages[i])
        }


        LazyColumn(content = {
            items(mixedList.size) { index ->
                val item = mixedList[index]
                when (item) {
                    is FunFact -> FunFactCard(fact = item, modifier = Modifier.padding(8.dp))
                    is Community -> CommunityCard(community = item, modifier = Modifier.padding(8.dp))
                    is Article -> ArticleCard(article = item, modifier = Modifier.padding(8.dp))
                    is Image -> ImageCard(image = item, modifier = Modifier.padding(8.dp))
                }
            }
        },
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
    }


}

@Composable
fun FunFactCard(
    fact: FunFact,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(fact.likes) } // Liczba polubień
    var isLiked by remember { mutableStateOf(false) } // Stan przycisku "like"

    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Tekst z faktem
        Text(
            text = fact.fact,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        // Sekcja "like" z ikoną i licznikiem
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    likes += if (isLiked) 1 else -1 // Dodaj lub odejmij polubienie
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isLiked) "Unlike" else "Like",
                    tint = if (isLiked) Color.Blue else Color.Gray
                )
            }
            Text(text = "$likes likes", fontSize = 16.sp, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

@Composable
fun CommunityCard(
    community: Community,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(community.likes) }
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Logo lub obraz społeczności
        AsyncImage(
            model = community.photoUrl,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Community Image",
            modifier = Modifier.fillMaxWidth()
        )

        // Nazwa społeczności i opis
        Text(
            text = community.title,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = community.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )

        // Przyciski like
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    likes += if (isLiked) 1 else -1
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isLiked) "Unlike" else "Like",
                    tint = if (isLiked) Color.Green else Color.Gray
                )
            }
            Text(text = "$likes likes", fontSize = 16.sp, modifier = Modifier.padding(start = 4.dp))
        }
    }
}


@Composable
fun ArticleCard(
    article: Article,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(article.likes) }
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Tytuł artykułu
        Text(
            text = article.title,
            fontSize = 22.sp,
            modifier = Modifier.padding(8.dp)
        )

        // Treść artykułu
        Text(
            text = article.content,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )

        // Przyciski like
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    likes += if (isLiked) 1 else -1
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isLiked) "Unlike" else "Like",
                    tint = if (isLiked) Color.Magenta else Color.Gray
                )
            }
            Text(text = "$likes likes", fontSize = 16.sp, modifier = Modifier.padding(start = 4.dp))
        }
    }
}


@Composable
fun ImageCard(
    image: Image,
    modifier: Modifier = Modifier
) {
    var likes by remember { mutableStateOf(image.likes) }
    var isLiked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Wyświetlenie obrazu
        AsyncImage(
            model = image.url,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image Content",
            modifier = Modifier.fillMaxWidth()
        )

        // Przyciski like
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            IconButton(
                onClick = {
                    isLiked = !isLiked
                    likes += if (isLiked) 1 else -1
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isLiked) "Unlike" else "Like",
                    tint = if (isLiked) Color.Cyan else Color.Gray
                )
            }
            Text(text = "$likes likes", fontSize = 16.sp, modifier = Modifier.padding(start = 4.dp))
        }
    }
}

