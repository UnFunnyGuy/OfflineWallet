package com.sarathexp.offlinewallet.presentation.component

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.text.isDigitsOnly
import com.sarathexp.offlinewallet.presentation.util.InputEvent
import com.sarathexp.offlinewallet.presentation.util.TypeInputEvent

@Composable
fun NormalTextField(
    @StringRes label: Int,
    inputEvent: InputEvent,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (FocusManager) -> Unit = { it.moveFocus(FocusDirection.Next) },
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = inputEvent.value,
        onValueChange = inputEvent.onValueChange,
        label = { Text(text = stringResource(id = label)) },
        placeholder = { placeholder?.let { Text(it) } },
        singleLine = true,
        keyboardOptions = keyboardOptions ?: KeyboardOptions(imeAction = imeAction),
        keyboardActions =
            keyboardActions
                ?: KeyboardActions(
                    onDone = { onImeAction(focusManager) },
                    onNext = { onImeAction(focusManager) },
                ),
        modifier = modifier,
        visualTransformation = visualTransformation,
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText =
            supportingText?.let { { Text(text = it, color = MaterialTheme.colorScheme.error) } },
    )
}

@Composable
fun <T> NormalNumberTextField(
    @StringRes label: Int,
    inputEvent: TypeInputEvent<T>,
    transformer: TypeInputTransformer<T>,
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions? = null,
    keyboardType: KeyboardType = KeyboardType.Decimal,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: (FocusManager) -> Unit = { it.moveFocus(FocusDirection.Next) },
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = transformer.transform(inputEvent.value),
        onValueChange = {
            if (it.isDigitsOnly()) {
                inputEvent.onValueChange(transformer.reverseTransform(it))
            }
        },
        label = { Text(text = stringResource(id = label)) },
        placeholder = { placeholder?.let { Text(it) } },
        singleLine = true,
        // TODO: Change This
        keyboardOptions =
            KeyboardOptions(
                imeAction = imeAction,
                keyboardType = keyboardType,
            ),
        keyboardActions =
            keyboardActions
                ?: KeyboardActions(
                    onDone = { onImeAction(focusManager) },
                    onNext = { onImeAction(focusManager) },
                ),
        modifier = modifier,
        visualTransformation = visualTransformation,
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText =
            supportingText?.let { { Text(text = it, color = MaterialTheme.colorScheme.error) } },
    )
}

@Deprecated("Find a better way to do this, Unnecessary complexity")
data class TypeInputTransformer<T>(
    val transform: (T) -> String,
    val reverseTransform: (String) -> T
)
