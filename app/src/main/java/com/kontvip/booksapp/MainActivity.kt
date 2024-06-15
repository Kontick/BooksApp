package com.kontvip.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.kontvip.booksapp.ui.theme.BooksAppTheme
import com.kontvip.common.navigation.NavHostMain
import com.kontvip.list.core.ListRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
                NavHostMain(
                    navController = rememberNavController(),
                    startDestination = ListRoute()
                )
            }
        }
    }
}