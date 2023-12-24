package com.sarathexp.offlinewallet.app.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import kotlin.random.Random

/**
 * Base UIState
 * This is a marker interface for UIState, all UIState must implement this interface
 */
interface UIState

/**
 * Base UIEvent
 * This is a marker interface for UIEvent, all UIEvent must implement this interface
 */
interface UIEvent {
    /**
     * Required for future use
     */
    val key
        get() = Random.nextLong(0, 100000000000000)
}

/**
 * Base ActionEvent
 * This is a marker interface for ActionEvent, all ActionEvent must implement this interface
 */
interface ActionEvent

/**
 * Base ViewModel
 * This is a base class for all ViewModel
 * @param State the UIState
 * @param Event the UIEvent
 * @param Action the ActionEvent
 */

abstract class BaseViewModel<State : UIState, Event : UIEvent, Action : ActionEvent> : ViewModel() {

    /**
     * This is an optional logger tag
     */
    protected val logger by lazy {
        Timber.tag(this::class.simpleName ?: this::class.java.simpleName)
    }


    private val initialState: State by lazy { initialState() }

    /**
     * This is the initial state of the ViewModel, all ViewModel must override this method
     */
    abstract fun initialState(): State


    /**
     * This method will be called when an ActionEvent is emitted
     * @param action the ActionEvent that is emitted from the UI(Should be user interaction)
     */
    abstract fun onActionEvent(action: Action)

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _uiEventFlow = Channel<Event?>()
    val uiEvent = _uiEventFlow.receiveAsFlow()

    private val _uiActionEvent = Channel<Action>()
    private val uiActionEvent = _uiActionEvent.receiveAsFlow()

    init {
        collectActionEvent()
    }

    protected val currentState: State
        get() = uiState.value

    protected fun update(updatedState: State.() -> State) = _uiState.update(updatedState)

    @OptIn(DelicateCoroutinesApi::class)
    protected fun sendUIEvent(event: Event?) {
        if (!_uiEventFlow.isClosedForReceive) {
            launch { _uiEventFlow.send(event) }
        }
    }

    fun resetUIEvent() {
        sendUIEvent(null)
    }


    private fun collectActionEvent() {
        launch { uiActionEvent.collectLatest { onActionEvent(it) } }
    }

    fun closeUIEvent() {
        launch { _uiEventFlow.close() }
    }
}

interface CommonUIEvent : UIEvent {
    data class Loading(val message: String = "") : CommonUIEvent
    data class Error(val errorMessage: String = "") : CommonUIEvent
    object NavigateUp : CommonUIEvent
    object NavigateNext : CommonUIEvent
}

