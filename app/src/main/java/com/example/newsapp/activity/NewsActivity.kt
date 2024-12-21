package com.example.newsapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.R
import com.example.newsapp.fragments.CategoriesFragment
import com.example.newsapp.fragments.NewsFragmentContent
import com.example.newsapp.model.CategoryFragmentScreen
import com.example.newsapp.model.NewsFragmentScreen
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.utils.NewsAppBar
import com.example.newsapp.utils.NewsDrawerSheet
import dagger.hilt.android.AndroidEntryPoint
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



