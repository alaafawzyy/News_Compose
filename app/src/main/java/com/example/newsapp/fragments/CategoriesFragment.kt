package com.example.newsapp.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.constants.Constant
import com.example.newsapp.model.Category
import com.example.newsapp.model.NewsFragmentScreen
import com.example.newsapp.ui.theme.gray2

@Composable
fun CategoriesFragment(modifier: Modifier = Modifier, navHostController: NavHostController){
    Column (modifier=modifier.fillMaxSize())
    {
        Text(text= stringResource(R.string.pick_your_category) +
                stringResource(R.string.of_interest),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = gray2,
            modifier = Modifier.padding(top = 30.dp, start = 28.dp, bottom = 24.dp))

        CategoriesGrid(navHostController = navHostController)

    }
}

@Composable
fun CategoriesGrid(navHostController: NavHostController){
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(Constant.categores.size){ position ->
            CategoriesCard(Constant.categores.get(position),position,navHostController)
        }}
}

@Composable
fun CategoriesCard(category: Category, position:Int, navHostController: NavHostController){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 18.dp,
            start = if(position%2==0) 40.dp else 10.dp,
        end = if(position%2!=0) 40.dp else 10.dp,),
        colors = CardDefaults.cardColors(containerColor = colorResource(category.BackGroundColour)),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp,
            bottomEnd = if(position%2==0) 0.dp else 24.dp,
            bottomStart =if(position%2==0) 24.dp else 0.dp ),
        onClick = {
            navHostController.navigate("${NewsFragmentScreen.RouteName}/${category.apiId}")
        }

    ) {
        Image(painter = painterResource( category.drawableResourseId),
            contentDescription = stringResource(category.titleResourseId),
            contentScale = ContentScale.Crop,

            modifier = Modifier.height(150.dp)
                .align(Alignment.CenterHorizontally)
                .padding(12.dp)
        )
        Text(text = stringResource( category.titleResourseId), modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 22.sp, fontWeight = FontWeight.Normal, color = Color.White
        )
    }
}


