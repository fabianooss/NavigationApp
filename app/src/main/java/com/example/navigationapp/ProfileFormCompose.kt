package com.example.navigationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileFormCompose(navController: NavHostController, id: Int?) {
    Column() {
        Text(text = "Profile Form ${id}")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Voltar")

        }
    }
}