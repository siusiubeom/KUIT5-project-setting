package com.example.kuitandroidapiexample.ui.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.kuitandroidapiexample.util.DataStoreManager
import com.example.kuitandroidapiexample.util.SharedPreferencesManager
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(modifier: Modifier = Modifier){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("input value") }
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    DataStoreManager.saveValue(context = context, key = "sample_key", value = input)
                }
            }
        ) {
            Text("pref 저장")
        }
        Button(
            onClick = {
                coroutineScope.launch() {
                    DataStoreManager.deleteValue(context = context, key = "sample_key")
                }
            }
        ) {
            Text("pref 삭제")
        }
        Button(
            onClick = {
                coroutineScope.launch() {
                    result = DataStoreManager.getValue(context = context, key = "sample_key").first() ?: "없음"
                }
            }
        ) {
            Text("pref 조회")
        }

        Text("결과: $result")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreferenceScreenPreview(){
    PreferencesScreen()
}