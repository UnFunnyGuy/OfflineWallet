package com.sarathexp.offlinewallet.presentation.composable.card

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * A card that flips when clicked.
 * ref - https://medium.com/huawei-developers/how-to-create-a-flip-card-effect-using-jetpack-compose-6bffbfd07dbd
 */
@Composable
fun FlipCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    axis: RotationAxis = RotationAxis.AxisY,
    colors: CardColors = CardDefaults.cardColors(),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {

    //TODO: change things related to card size

    val rotation by
        animateFloatAsState(
            targetValue = cardFace.angle,
            animationSpec =
                tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing,
                ),
            label = "rotation",
        )

    Card(
        onClick = { onClick(cardFace) },
        modifier =
            modifier.graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation
                } else {
                    rotationY = rotation
                }
                cameraDistance = 12f * density
            },
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
    ) {
        val backContentHeight = remember {
            mutableStateOf(0.dp)
        }
        val density = LocalDensity.current
        if (rotation <= 90f) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .onSizeChanged {
                        backContentHeight.value = with(density) { it.height.toDp() }
                    }
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(backContentHeight.value)
                    .graphicsLayer {
                    if (axis == RotationAxis.AxisX) {
                        rotationX = 180f
                    } else {
                        rotationY = 180f
                    }
                },
            ) {
                back()
            }
        }
    }
}
