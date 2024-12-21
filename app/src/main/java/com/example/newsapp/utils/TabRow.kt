package com.example.newsapp.utils

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.R
import com.example.newsapp.activity.NewsViewModel

import com.example.newsapp.ui.theme.green
import retrofit2.Call
import retrofit2.Response

@Composable
fun NewsSourcesTabRow(categoryId:String,onTabSelected:(sourceID:String)->Unit) {

val viewModel:NewsViewModel= viewModel()

    LaunchedEffect(Unit) {
      viewModel.fetchSource(categoryId)
    }

    Column(
        modifier = Modifier
          .fillMaxWidth()
            .padding(top = 8.dp)


    ) {
        ScrollableTabRow(
            selectedTabIndex = viewModel.selectedIndexState.intValue,
            divider = {},
            indicator = {},
            edgePadding = 8.dp,
        ) {
            LaunchedEffect(Unit) {
                if (viewModel.newsSources.isNotEmpty())
                onTabSelected(viewModel.newsSources[0].id ?: "")
            }
            viewModel.newsSources.forEachIndexed { index, source ->

                Tab(
                    modifier = Modifier.padding(end = 8.dp),
                    selected = viewModel.selectedIndexState.intValue == index,
                    onClick = { viewModel.selectedIndexState.intValue = index
                              onTabSelected(source.id?:"")
                              },
                    selectedContentColor = Color.White,
                    unselectedContentColor = green
                ) {
                    Text(

                        text = source.name?:"",
                        modifier = if (viewModel.selectedIndexState.intValue == index)
                            Modifier
                                .padding(2.dp)
                                .background(green, CircleShape)
                                .padding(horizontal = 12.dp, vertical = 8.dp)

                        else
                            Modifier
                                .padding(2.dp)
                                .border(2.dp, green, CircleShape)
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }

        }
    }
}

