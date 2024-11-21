package com.example.newsapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.R
import com.example.newsapp.activity.NewsActivity
import com.example.newsapp.ui.theme.NewsAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Handler(Looper.getMainLooper()).postDelayed({
                  val intent=Intent(this, NewsActivity::class.java)
                    startActivity(intent)
                    finish()
                },2500)
               SplashScreen()            }
        }
    }
}
@Composable
fun SplashScreen(){
     Column (modifier = Modifier
         .fillMaxSize()
         .paint(
             painter = painterResource(R.drawable.pattern),
             contentScale = ContentScale.Crop
         ),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ){
         Spacer(modifier = Modifier.weight(1F))
         Image(painter = painterResource(R.drawable.logo), contentDescription = stringResource(R.string.app_logo_image),
             modifier = Modifier.fillMaxHeight(0.35F),
             contentScale = ContentScale.Crop)
         Spacer(modifier = Modifier.weight(1F))
         Image(painter = painterResource(R.drawable.appsignature),
               contentDescription = stringResource(R.string.app_signature),
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth(0.5F)
         )
     }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen()
}


