package com.sarathexp.offlinewallet.presentation.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleBarScreen(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    childModifier: Modifier? = null,
    childPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    leftIcon: ImageVector? = Icons.Filled.ArrowBackIosNew,
    onLeftIconClick: () -> Unit = {},
    rightIcon: ImageVector? = null,
    onRightIconClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Top
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = onLeftIconClick, modifier = Modifier) {
                    Icon(
                        imageVector = leftIcon ?: Icons.Filled.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                rightIcon?.let {
                    IconButton(onClick = { onRightIconClick?.invoke() }, modifier = Modifier) {
                        Icon(imageVector = it, contentDescription = null)
                    }
                }
            }

            Row(
                modifier =
                    Modifier.padding(horizontal = 32.dp).fillMaxWidth().align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = title),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

        Column(
            modifier = childModifier ?: Modifier.padding(childPadding).fillMaxSize(),
            content = content,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        )
    }
}
