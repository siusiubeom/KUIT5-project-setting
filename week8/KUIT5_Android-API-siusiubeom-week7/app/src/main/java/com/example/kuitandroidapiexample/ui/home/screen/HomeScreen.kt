package com.example.kuitandroidapiexample.ui.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuitandroidapiexample.ui.home.component.AnimalItem
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography

@Composable
fun HomeScreen(
    padding: PaddingValues,
    navigateToRegister: () -> Unit = {},
    navigateToDetail: (Int) -> Unit = {},
    navigateToPref: () -> Unit = {},
    viewModel: AnimalViewModel = viewModel()
) {
    val lazyState = rememberLazyListState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getTotalAnimalList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.matchParentSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "조회하기",
                    modifier = Modifier.align(Alignment.Center),
                    style = typography.semiBold.copy(
                        fontSize = 18.sp
                    )
                )
            }
            LazyColumn(
                state = lazyState,
                contentPadding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.animals) { animal ->
                    AnimalItem(
                        animalData = animal,
                        navigateToDetail = { navigateToDetail(animal.id) }
                    )
                }
            }
        }

        IconButton(
            modifier = Modifier
                .padding(end = 17.dp, bottom = 20.dp)
                .size(56.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colors.orange)
                .align(Alignment.BottomEnd),
            onClick = navigateToRegister
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                imageVector = Icons.Filled.Add,
                contentDescription = "등록하기 버튼",
                tint = colors.white
            )
        }
        IconButton(
            modifier = Modifier
                .padding(start = 17.dp, bottom = 20.dp)
                .size(56.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colors.orange)
                .align(Alignment.BottomStart),
            onClick = navigateToPref
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                imageVector = Icons.Filled.Build,
                contentDescription = "pref 버튼",
                tint = colors.white
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        padding = PaddingValues()
    )
}