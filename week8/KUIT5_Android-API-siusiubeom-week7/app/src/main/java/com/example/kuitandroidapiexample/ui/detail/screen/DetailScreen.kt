package com.example.kuitandroidapiexample.ui.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.kuitandroidapiexample.R
import com.example.kuitandroidapiexample.ui.common.TagChip
import com.example.kuitandroidapiexample.ui.detail.viewmodel.AnimalDetailViewModel
import com.example.kuitandroidapiexample.ui.home.viewmodel.AnimalViewModel
import com.example.kuitandroidapiexample.ui.model.AnimalType
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.colors
import com.example.kuitandroidapiexample.ui.theme.FindUTheme.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    padding: PaddingValues,
    index: Int,
    navigateToBack: () -> Unit = {},
    viewModel: AnimalDetailViewModel = viewModel()
) {
    /*val response by viewModel.animalDetailState
    val animalDetail = response?.data

    val delete by viewModel.animalDeleteState*/
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    val scope = rememberCoroutineScope()
    val snackBarHostState = remember {SnackbarHostState()}

    LaunchedEffect(index) {
        viewModel.getAnimalDetail(index)
    }

    LaunchedEffect(uiState.isDelete) {
        if(uiState.isDelete){
            navigateToBack()
        }
    }


    Scaffold(
    modifier = Modifier.fillMaxSize(),
    snackbarHost = {
        SnackbarHost(
            hostState = snackBarHostState
        )
    },
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(20.dp)
                                .clickable { navigateToBack() },
                            painter = painterResource(R.drawable.ic_chevron_left),
                            contentDescription = "뒤로 가기",
                            tint = Color.Unspecified
                        )
                    }
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp),
                        model = uiState.url ?: "",
                        contentDescription = "동물 사진"
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(304.dp)
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                ) {
                    Text(
                        text = uiState.animalName ?: "",
                        style = typography.semiBold.copy(fontSize = 24.sp),
                        modifier = Modifier.padding(start = 40.dp, top = 42.dp, bottom = 20.dp)
                    )

                    TagChip(
                        modifier = Modifier.padding(start = 40.dp),
                        animalType = uiState.animalType ?: AnimalType.PROTECT
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = colors.black,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clip(RoundedCornerShape(20.dp))
                            .shadow(
                                elevation = 10.dp,
                                spotColor = Color(0x1AA0A0A0),
                                ambientColor = Color(0x1AA0A0A0)
                            )
                            .height(89.dp)
                            .padding(horizontal = 40.dp, vertical = 20.dp)
                    ) {
                        Text(
                            text = "주소",
                            style = typography.semiBold.copy(
                                fontSize = 14.sp,
                                color = colors.orange
                            ),
                        )
                        Text(
                            text = uiState.address ?: "",
                            style = typography.semiBold.copy(fontSize = 14.sp),
                            modifier = Modifier.align(Alignment.BottomStart)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "신고자 : 조익성",
                            style = typography.semiBold.copy(fontSize = 14.sp),
                            modifier = Modifier.padding(start = 40.dp, top = 21.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(colors.red)
                                .padding(10.dp)
                                .clickable {
                                    scope.launch {
                                        snackBarHostState.showSnackbar("삭제가 완료되었습니다")
                                        viewModel.deleteAnimal(index)
                                        delay(300)
                                        navigateToBack()
                                    }
                                },
                        ) {
                            Text(
                                text = "삭제하기",
                                style = typography.semiBold.copy(fontSize = 20.sp),
                                color = colors.white,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

        }
    }
}




@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(PaddingValues(), 0)
}