package zed.rainxch.septemberminichallenges.timeline_painter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class TimelinePainterViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TimelinePainterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TimelinePainterState()
        )

    fun onAction(action: TimelinePainterAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}