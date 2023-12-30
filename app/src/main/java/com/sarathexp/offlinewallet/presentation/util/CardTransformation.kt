package com.sarathexp.offlinewallet.presentation.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

//TODO: Fix NormalCardFormatter

/**
 * A [VisualTransformation] for DEBIT,CREDIT cards.
 */
class CardTransformation : VisualTransformation {

    private constructor(formatter: CardFormatter) : super() {
        this.formatter = formatter
    }

    companion object {
        /**
         * Creates a [VisualTransformation] that masks the input.
         *
         * @param separator the separator to use between the groups of digits. Defaults to "-".
         * @param displayOnlyLastFourDigits if true, only the last four digits will be displayed.
         * Defaults to false.
         * @param digitMask the mask to use for the digits. Defaults to "*".
         */
        fun normalCards(
            separator: String = "-",
            displayOnlyLastFourDigits: Boolean = false,
            digitMask: String = "*"
        ) = CardTransformation(NormalCardFormatter(separator, displayOnlyLastFourDigits, digitMask))

        val ExpiryDate = CardTransformation(ExpiryDateFormatter())

        val SpecialCards = CardTransformation(SpecialCardFormatter())

    }


    private val formatter: CardFormatter

    override fun filter(text: AnnotatedString): TransformedText {
        return formatter.format(text)
    }


    interface CardFormatter {
        fun format(text: AnnotatedString): TransformedText

    }

    private class NormalCardFormatter(
        private val separator: String = "-",
        private val displayOnlyLastFourDigits: Boolean = false,
        private val digitMask: String = "*"
    ) : CardFormatter {

        /**
         * Formats the [text]
         *
         * @param text the text to format.
         * @return the formatted text.
         * @author https://github.com/SteliosPapamichail/CreditCardHelper/blob/master/CreditCardHelper/src/main/java/com/steliospapamichail/creditcardmasker/viewtransformations/CardNumberMask.kt
         */
        override fun format(
            text: AnnotatedString,
        ): TransformedText {

            val trimmed =
                if (text.text.length >= 16) text.text.substring(0..15) else text.text
            var out = ""
            for (i in trimmed.indices) {
                out += if (displayOnlyLastFourDigits && i !in 12..16) digitMask else trimmed[i]
                if (i == 3 || i == 7 || i == 11) out += separator
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 3 -> offset
                        offset <= 7 -> offset + 1
                        offset <= 11 -> offset + 2
                        offset <= 16 -> offset + 3
                        else -> 19
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 4 -> offset
                        offset <= 9 -> offset - 1
                        offset <= 14 -> offset - 2
                        offset <= 19 -> offset - 3
                        else -> 16
                    }
                }
            }

            return TransformedText(AnnotatedString(out), offsetMapping)
        }
    }

    private class SpecialCardFormatter : CardFormatter {
        override fun format(text: AnnotatedString): TransformedText {
            return TransformedText(text, OffsetMapping.Identity)
        }
    }

    private class ExpiryDateFormatter(
        private val separator: String = "/",
        private val displayOnlyLastTwoDigits: Boolean = false,
        private val digitMask: String = "*"
    ) : CardFormatter {

        /**
         * Formats the [text]
         *
         * @param text the text to format.
         * @return the formatted text.
         */
        override fun format(
            text: AnnotatedString,
        ): TransformedText {

            val trimmed =
                if (text.text.length >= 5) text.text.substring(0..4) else text.text
            var out = ""
            for (i in trimmed.indices) {
                out += if (displayOnlyLastTwoDigits && i !in 3..4) digitMask else trimmed[i]
                if (i == 1) out += separator
            }

            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 1 -> offset
                        offset <= 4 -> offset + 1
                        else -> 7
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 2 -> offset
                        offset <= 6 -> offset - 1
                        else -> 4
                    }
                }
            }

            return TransformedText(AnnotatedString(out), offsetMapping)
        }
    }
}

