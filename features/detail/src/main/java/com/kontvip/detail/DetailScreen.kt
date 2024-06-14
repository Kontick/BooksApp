package com.kontvip.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(navController: NavController, bookId: String) {
}

@Composable
@Preview
fun DetailScreenPreview() {
    DetailScreen(rememberNavController(), "1")
}