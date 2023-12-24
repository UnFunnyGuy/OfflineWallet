package com.sarathexp.offlinewallet.app.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Launches a coroutine in the [BaseViewModel.viewModelScope].
 * @param operation lambda that will be executed in the [BaseViewModel.viewModelScope].
 * @return [Job].
 */
fun BaseViewModel<*, *, *>.launch(operation: suspend () -> Unit): Job {
    return viewModelScope.launch { operation() }
}

/**
 * Collects the [UIState] from the [BaseViewModel].
 * @return [UIState].
 * @see [UIState]
 * @see [BaseViewModel.uiState]
 */
@Composable
fun <S : UIState, VM : BaseViewModel<S, *, *>> VM.collectState() =
    uiState.collectAsStateWithLifecycle()


/**
 * Collects the [UIEvent] from the [BaseViewModel].
 * @return [UIEvent].
 * @see [UIEvent]
 * @see [BaseViewModel.uiEvent]
 */
@Composable
fun <E : UIEvent, VM : BaseViewModel<*, E, *>> VM.collectEvent() =
    uiEvent.collectAsStateWithLifecycle(null)

/**
 * Collects the [UIEvent] from the [BaseViewModel] and calls the [events] lambda.
 * @param onDispose lambda that will be called when the composable is disposed.
 * @param closeChannelOnDispose if true, the [BaseViewModel.closeUIEvent] will be called when the composable is disposed.
 * if onDispose is not null, this parameter will be ignored.
 * @param events lambda that will be called when the [UIEvent] is emitted.
 */
@Composable
fun <E : UIEvent, VM : BaseViewModel<*, E, *>> VM.UIEventHandler(
    onDispose: (() -> Unit)? = null,
    closeChannelOnDispose: Boolean = true,
    events: (E?) -> Unit
) {

    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        scope.launch { uiEvent.collect { events(it) } }
        onDispose {
            onDispose?.invoke()
                ?: run {
                    if (closeChannelOnDispose) {
                        closeUIEvent()
                    }
                }
        }
    }
}
