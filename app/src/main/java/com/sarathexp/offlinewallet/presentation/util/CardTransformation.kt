package com.sarathexp.offlinewallet.presentation.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

//TODO: Fix NormalCardFormatter
class CardTransformation : VisualTransformation {

    private constructor(formatter: CardFormatter) : super() {
        this.formatter = formatter
    }

    companion object {

        val NormalCards = CardTransformation(NormalCardFormatter())
        val SpecialCards = CardTransformation(SpecialCardFormatter())

    }


    private val formatter: CardFormatter

    override fun filter(text: AnnotatedString): TransformedText {
        return formatter.format(text)
    }


    interface CardFormatter {
        fun format(text: AnnotatedString): TransformedText
    }

    private class NormalCardFormatter : CardFormatter {
        override fun format(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
            var out = ""

            for (i in trimmed.indices) {
                out += trimmed[i]
                if (i % 4 == 3 && i != 15) out += "-"
            }
            val creditCardOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 3) return offset
                    if (offset <= 7) return offset + 1
                    if (offset <= 11) return offset + 2
                    if (offset <= 16) return offset + 3
                    return 19
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 4) return offset
                    if (offset <= 9) return offset - 1
                    if (offset <= 14) return offset - 2
                    if (offset <= 19) return offset - 3
                    return 16
                }
            }

            return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
        }
    }

    private class SpecialCardFormatter : CardFormatter {
        override fun format(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
            var out = ""

            for (i in trimmed.indices) {
                out += trimmed[i]
                if (i % 4 == 3 && i != 15) out += "+"
            }
            val creditCardOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 3) return offset
                    if (offset <= 7) return offset + 1
                    if (offset <= 11) return offset + 2
                    if (offset <= 16) return offset + 3
                    return 19
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 4) return offset
                    if (offset <= 9) return offset - 1
                    if (offset <= 14) return offset - 2
                    if (offset <= 19) return offset - 3
                    return 16
                }
            }

            return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return other is CardTransformation
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}

