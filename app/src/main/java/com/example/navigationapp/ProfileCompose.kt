package com.example.navigationapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileCompose(navController: NavHostController) {
    Column() {
        Text(text = "Profile")
        Button(onClick = { navController.navigate("form/1") }) {
            Text(text = "Form 1")
        }
        Button(onClick = { navController.navigate("form/2") }) {
            Text(text = "Form 2")
        }
        Button(onClick = { navController.navigate("form/3") }) {
            Text(text = "Form 3")
        }

        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Voltar")
        }
    }
}