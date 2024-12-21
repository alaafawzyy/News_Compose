package com.example.newsapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.green
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NewsDrawerSheet(onSettingClick: () -> Unit, onCategoryClick: () -> Unit) {
    val systemUiController = rememberSystemUiController()

    // تغيير لون شريط الحالة العلوي إلى الأخضر
    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(color = green)
    }

    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7F)) {
        Column(
            modifier = Modifier
                .background(green)
                .fillMaxWidth()
                .fillMaxHeight(0.2F)
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.newsapp),
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        DrawerSheetItemTextIcon(icon = R.drawable.ic_category, text = R.string.categories, onItemClick = { onCategoryClick() })
        DrawerSheetItemTextIcon(R.drawable.ic_settings, R.string.settings, onItemClick = { onSettingClick() })
    }
}



@Composable
fun DrawerSheetItemTextIcon(icon:Int,text:Int,onItemClick:()->Unit){
            Row(
            verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable(onClick = onItemClick)
    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(R.string.categories)
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            stringResource(id = text),
            style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        )
    }
}

@Preview
@Composable
fun DrawerSheetItemTextIconPreview(){
    DrawerSheetItemTextIcon(R.drawable.ic_menu, R.string.news_app, onItemClick={})
}