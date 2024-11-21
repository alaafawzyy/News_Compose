package com.example.newsapp.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.R
import com.example.newsapp.activity.NewsViewModel
import com.example.newsapp.api.ApiManager
import com.example.newsapp.constants.Constant
import com.example.newsapp.model.Article
import com.example.newsapp.model.ArticleResponce
import com.example.newsapp.ui.theme.gray
import com.example.newsapp.ui.theme.lightgray
import com.example.newsapp.utils.NewsSourcesTabRow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsFragmentContent(modifier: Modifier = Modifier, categoryId: String) {
  val viewModel:NewsViewModel= viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.pattern), contentScale = ContentScale.Crop)
    ) {

        NewsSourcesTabRow(categoryId) { sourceId ->
                 viewModel.fetchNews(sourceId)
        }

        NewsList(viewModel.newsStateItem.toList())
    }
}



@Composable
fun NewsList(list:List<Article>) {
    LazyColumn (horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()){
        items (list.size){ position ->
            NewsCard(list[position])

        } }}


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun NewsCard(newsData: Article){
        Card (modifier = Modifier.padding(horizontal = 16.dp, vertical =12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)){

            GlideImage(model = newsData.urlToImage,
                contentDescription = stringResource(R.string.news_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 1F)
                    .clip(RoundedCornerShape(10.dp)))

            Text(text = newsData.source?.name?:"", style = TextStyle(color = lightgray), fontSize = 14.sp,fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 13.dp))
            Text(text = newsData.title?:"", style = TextStyle(color = gray), fontSize = 14.sp, fontWeight = FontWeight.Medium,modifier = Modifier.padding(top = 3.dp))
            Text(text = newsData.publishedAt?:"", style = TextStyle(color = lightgray), fontSize = 14.sp,fontWeight = FontWeight.Medium, modifier = Modifier.align(
                Alignment.End).padding(top = 4.dp, bottom = 8.dp))
        }

    }


