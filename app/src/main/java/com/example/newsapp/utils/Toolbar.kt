package com.example.newsapp.utils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppBar(onNavigationItemClick :() ->Unit) {
    TopAppBar(

        title = { Text(text = stringResource(R.string.news_app),
            style = TextStyle(color = Color.White, fontSize = 22.sp, textAlign = TextAlign.Center),
          modifier = Modifier.fillMaxWidth()
        ) },

        modifier = Modifier.clip(RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp, bottomEnd = 25.dp, bottomStart = 25.dp)),

        navigationIcon = {
            Image(painter = painterResource( R.drawable.ic_menu),
                contentDescription = stringResource(R.string.menu_icon),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(25.dp)
                    .clickable {
                        onNavigationItemClick() }) },

        actions = {
            Image(painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.appbar_search),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(20.dp)) },


        colors = TopAppBarDefaults.topAppBarColors(containerColor = green),






    )
}

