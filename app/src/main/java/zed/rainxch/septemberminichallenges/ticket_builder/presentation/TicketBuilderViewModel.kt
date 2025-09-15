package zed.rainxch.septemberminichallenges.ticket_builder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class TicketBuilderViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TicketBuilderState())
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
            initialValue = TicketBuilderState()
        )

    fun onAction(action: TicketBuilderAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}