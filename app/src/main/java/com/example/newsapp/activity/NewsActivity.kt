package com.example.newsapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.R
import com.example.newsapp.constants.Constant
import com.example.newsapp.fragments.CategoriesFragment
import com.example.newsapp.fragments.NewsFragmentContent
import com.example.newsapp.model.Category
import com.example.newsapp.model.CategoryFragmentScreen
import com.example.newsapp.model.NewsFragmentScreen
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.gray2
import com.example.newsapp.utils.NewsAppBar

import com.example.newsapp.utils.NewsDrawerSheet
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
@AndroidEntryPoint
class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                   NewsScreen()
            }
        }
    }
}

@Composable
fun NewsScreen() {
    val navController= rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(

        drawerContent = {NewsDrawerSheet(
                         onSettingClick = {}, onCategoryClick = {
                         navController.popBackStack()
                    if(navController.currentDestination?.route != CategoryFragmentScreen.RouteName)
                         navController.navigate(CategoryFragmentScreen.RouteName)
                         scope.launch { drawerState.close() } }) },

        drawerState = drawerState
    )

    {
        Scaffold(topBar = { NewsAppBar { scope.launch { drawerState.open() }}})

        { paddingValues ->
                  NavHost(navController=navController,
                     startDestination = CategoryFragmentScreen.RouteName ,
                      modifier = Modifier.
                          padding(top = paddingValues.calculateTopPadding())
                          .paint(
                              painter = painterResource(R.drawable.pattern),
                              contentScale = ContentScale.Crop),
                  ){
                      composable(CategoryFragmentScreen.RouteName){
                          CategoriesFragment(navHostController =navController)
                      }
                      composable("${NewsFragmentScreen.RouteName}/{category_id}",
                          arguments = listOf(navArgument("category_id"){
                              type= NavType.StringType
                          })
                      ) {navbackstackentry ->
                          val categoryId=navbackstackentry.arguments?.getString("category_id")
                          NewsFragmentContent(categoryId=categoryId?:"")
                      }

                  }
        }     }
    }



