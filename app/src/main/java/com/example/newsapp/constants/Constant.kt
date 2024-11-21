package com.example.newsapp.constants

import com.example.newsapp.R
import com.example.newsapp.model.Category


object Constant {
    val Api_Key="5803d5116fc14cd88d136407e2c8113b"
    val categores= listOf(
        Category(
            apiId = "sports",
            drawableResourseId = R.drawable.sports,
            titleResourseId = R.string.sports,
            BackGroundColour = R.color.red
        ),
        Category(
            apiId = "technology",
            drawableResourseId = R.drawable.politics,
            titleResourseId = R.string.technology,
            BackGroundColour = R.color.blue
        ),
        Category(
            apiId = "Health",
            drawableResourseId = R.drawable.health,
            titleResourseId = R.string.health,
            BackGroundColour = R.color.pink
        ),
        Category(
            apiId = "business",
            drawableResourseId = R.drawable.bussines,
            titleResourseId = R.string.business ,
            BackGroundColour = R.color.brown_orange
        ),
        Category(
            apiId = "general",
            drawableResourseId = R.drawable.environment,
            titleResourseId = R.string.general,
            BackGroundColour = R.color.baby_blue
        ),
        Category(
            apiId = "science",
            drawableResourseId = R.drawable.science,
            titleResourseId = R.string.science,
            BackGroundColour = R.color.yellow
        ),



            )
}