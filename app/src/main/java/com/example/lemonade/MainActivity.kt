package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonTree()
            }
        }
    }
}

@Composable
fun LemonTree() {
    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(1) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    text = R.string.lemon_select,
                    image = R.drawable.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCount = (2..5).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    text = R.string.squeeze_lemon,
                    image = R.drawable.lemon_squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                    text = R.string.drink_lemon,
                    image = R.drawable.lemon_drink,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }
            4 -> {
                LemonTextAndImage(
                    text = R.string.restart,
                    image = R.drawable.lemon_restart,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    text: Int,
    image: Int,
    onImageClick: () -> Unit,
//    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = stringResource(id = text),
            fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonTree()
    }
}