package com.example.fitnessapplication


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MacronutrientScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 40.dp)
    ) {
        AgeRow()
        Spacer(modifier = Modifier.height(8.dp))
        GenderRow()
        Spacer(modifier = Modifier.height(8.dp))
        HeightRow()
        Spacer(modifier = Modifier.height(8.dp))
        WeightRow()
        Spacer(modifier = Modifier.height(20.dp))
        ActivityRow()
        Spacer(modifier = Modifier.height(20.dp))
        GoalRow()
    }
}

@Composable
fun AgeRow() {
    Row() {
        Text(text = "Age")
        OutlinedTextField(value = "", onValueChange = {})
    }
}

@Composable
fun GenderRow() {
    Row{Text(text = "Sex")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "Male")
        RadioButton(selected = false, onClick = { /*TODO*/ })
        Text(text = "Female")
        RadioButton(selected = false, onClick = { /*TODO*/ })}

}

@Composable
fun HeightRow(){
    Row{
        Text(text = "Height")
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = "", onValueChange = {})
    }
}

@Composable
fun WeightRow(){
    Row{
        Text(text = "Weight")
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = "", onValueChange = {})
    }
}

@Composable
fun ActivityRow(){
    Row{
        Text(text = "Activity")
        Spacer(modifier = Modifier.padding(16.dp))
        DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { /* Handle edit! */ },
                leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = { /* Handle settings! */ },
                leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) }
            )
        }
    }
}

@Composable
fun GoalRow(){
    Row{
        Text(text = "Your Goal")
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = "", onValueChange = {})
    }
}