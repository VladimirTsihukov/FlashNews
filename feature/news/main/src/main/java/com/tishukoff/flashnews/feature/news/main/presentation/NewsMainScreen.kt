package com.tishukoff.flashnews.feature.news.main.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tishukoff.flashnews.news_data.api.models.ArticlesDataState
import com.tishukoff.flashnews.news_data.api.models.ArticlesVo
import com.tishukoff.flashnews.news_ui_kit.FlashNewsTheme

@Composable
fun NewsMainScreen() {
    val viewModel: NewsViewModel = viewModel()

    NewsMainScreen(state = viewModel.articles)
}

@Composable
internal fun NewsMainScreen(
    state: State<ArticlesDataState>,
) {
    val stateValue = state.value

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        if (stateValue is ArticlesDataState.Loading || stateValue is ArticlesDataState.DatabaseSuccess) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
            )
        }

        when (stateValue) {
            is ArticlesDataState.Loading -> {
                Log.d("Logger", "Init Loading")
                Text(text = "Loading", fontSize = 32.sp)
            }

            is ArticlesDataState.Error -> {
                Log.d("Logger", "Init Error")
                Text(text = "Error", fontSize = 32.sp)
            }

            is ArticlesDataState.ServerSuccess -> {
                Log.d("Logger", "Init ServerSuccess")
                Articles(stateValue.articles)
            }

            is ArticlesDataState.DatabaseSuccess -> {
                Log.d("Logger", "Init DatabaseSuccess")
                Articles(stateValue.articles)
            }
        }
    }
}

@Composable
private fun Articles(
    articles: List<ArticlesVo>,
) {
    LazyColumn {
        items(articles.size) {
            ArticleItem(articles[it])
        }
    }
}

@Composable
private fun ArticleItem(
    article: ArticlesVo,
) {
    Row(modifier = Modifier.padding(16.dp)) {
        if (article.urlToImage.isNotBlank()) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop,
            )
        }

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = article.title,
                style = FlashNewsTheme.typography.headlineMedium,

                maxLines = 1
            )
            Text(
                text = article.description,
                style = FlashNewsTheme.typography.bodyMedium,
                maxLines = 3
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun NewsMainScreenPreview() {
    FlashNewsTheme {
        NewsMainScreen(state = mutableStateOf(ArticlesDataState.ServerSuccess(ArticlesVo.LIST_FOR_PREVIEW)))
    }
}
