package com.example.newsapp.activity
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapp.R
import com.example.newsapp.activity.ui.theme.NewsAppTheme
import com.example.newsapp.model.Article
import com.example.newsapp.ui.theme.gray
import com.example.newsapp.ui.theme.lightgray

class NewsDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val article: Article? =
                    intent.getSerializableExtra("article") as? Article  // تأكد من تحويله إلى Article

                if (article != null) {
                    ShowNewsDetails(article)
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
  fun ShowNewsDetails(article: Article) {
        Column (modifier = Modifier.fillMaxSize()){
            GlideImage(
                model = article.urlToImage,
                contentDescription = stringResource(R.string.news_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 2.2F)
                    .clip(RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp)))

    Text(
        text = article.title ?: "",
        style = TextStyle(color = Color.Black),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 30.dp,top = 20.dp, end = 30.dp)
    )
    Text(
        text = article.content ?: "",
        style = TextStyle(color = Color.Gray),
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(start = 30.dp,top = 20.dp, bottom = 8.dp, end = 30.dp)
    )

        }}

