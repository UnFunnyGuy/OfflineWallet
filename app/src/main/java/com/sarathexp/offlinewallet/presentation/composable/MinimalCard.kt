package com.sarathexp.offlinewallet.presentation.composable

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarathexp.offlinewallet.R
import com.sarathexp.offlinewallet.domain.model.card.CardNetwork
import com.sarathexp.offlinewallet.presentation.composable.card.CardFace
import com.sarathexp.offlinewallet.presentation.composable.card.FlipCard
import com.sarathexp.offlinewallet.presentation.util.CardTransformation

@Composable
fun MinimalCard(
    holder: String,
    number: String,
    expiration: String,
    cvv: String,
    cardNetwork: CardNetwork,
    modifier: Modifier = Modifier,
    bgColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    secondaryTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f),
) {

    var cardFace by remember { mutableStateOf(CardFace.Front) }

    val cardIcon =
        remember(cardNetwork) {
            when (cardNetwork) {
                CardNetwork.MASTER_CARD -> R.drawable.mnml_master_card
                else -> R.drawable.mnml_master_card
            }
        }

    val cardNumber =
        remember(number) {
            CardTransformation.normalCards(separator = " ")
                .filter(AnnotatedString(number.ifBlank { "XXXXXXXXXXXXXXXX" }))
        }

    val cardExpiry =
        remember(expiration) {
            CardTransformation.ExpiryDate.filter(AnnotatedString(expiration.ifBlank { "XXXX" }))
        }

    FlipCard(
        cardFace = cardFace,
        onClick = { cardFace = cardFace.next },
        modifier = modifier,
        colors =
            CardDefaults.elevatedCardColors(containerColor = bgColor, contentColor = textColor),
        front = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(PaddingValues(16.dp)),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = ImageVector.vectorResource(id = cardIcon),
                        contentDescription = null,
                        tint = textColor
                    )
                    Text(
                        text = cardNetwork.formattedCardName,
                        // style = MaterialTheme.typography.labelSmall,
                        color = textColor.copy(0.85f),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier.size(55.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.mnml_chip),
                        contentDescription = null,
                        tint = secondaryTextColor.copy(0.8f)
                    )
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.mnml_nfc),
                        contentDescription = null,
                        tint = textColor
                    )
                }

                Text(
                    text = cardNumber.text,
                    color = textColor.copy(0.85f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Start,
                    letterSpacing = 2.7.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = holder.uppercase().ifEmpty { "CARD HOLDER" },
                        color = textColor.copy(0.9f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.Start,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = cardExpiry.text,
                        color = textColor.copy(0.9f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Monospace,
                        textAlign = TextAlign.End,
                        letterSpacing = 2.sp
                    )
                }
            }
        },
        back = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier =
                        Modifier.padding(top = 16.dp)
                            .fillMaxWidth()
                            .background(color = Color.Black)
                            .height(50.dp),
                )
                Box(
                    modifier =
                        Modifier.padding(16.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = textColor.copy(0.25f),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = cvv,
                        color = textColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = "CVV",
                        color = textColor.copy(0.85f),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.padding(16.dp).size(36.dp).align(Alignment.End),
                    imageVector = ImageVector.vectorResource(id = cardIcon),
                    contentDescription = null,
                    tint = textColor
                )
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 640, heightDp = 360, name = "Minimal Card Preview")
@Composable
fun MinimalCardPreview() {
    MinimalCard(
        holder = "Sarath",
        number = "3245 5567 7845 1200",
        expiration = "05/31",
        cardNetwork = CardNetwork.MASTER_CARD,
        modifier = Modifier.fillMaxSize(),
        bgColor = Color.Blue.copy(alpha = 0.42f),
        textColor = Color(0xFF000000),
        secondaryTextColor = Color(0xFF000000).copy(alpha = 0.6f),
        cvv = "123"
    )
}
