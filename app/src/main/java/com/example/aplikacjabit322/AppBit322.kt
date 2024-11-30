package com.example.aplikacjabit322

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.phonebookapp.ui.navigation.AppBit322NavHost

@Composable
fun AppBit322(navController: NavHostController = rememberNavController()) {
    AppBit322NavHost(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bit322TopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    canClickButton: Boolean = false,
    onClickButton: () -> Unit = {},
    buttonIcon: ImageVector? = null,
    onClickSecondButton: () -> Unit = {},
    secondButtonIcon: ImageVector? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

//                PickFileButton()

                if (canNavigateBack) {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
                Text(text = title, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)

                if (secondButtonIcon != null) {
                    IconButton(onClick = onClickSecondButton) {
                        Icon(
                            imageVector = secondButtonIcon,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                if (canClickButton) {
                    IconButton(onClick = onClickButton) {
                        Icon(
                            imageVector = buttonIcon ?: Icons.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {


        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}