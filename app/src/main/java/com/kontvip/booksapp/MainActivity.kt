package com.kontvip.booksapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kontvip.booksapp.ui.theme.BooksAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme {
//                NavHostMain(
//                    navController = rememberNavController(),
//                    startDestination = ListRoute()
//                )
            }
        }
    }
}