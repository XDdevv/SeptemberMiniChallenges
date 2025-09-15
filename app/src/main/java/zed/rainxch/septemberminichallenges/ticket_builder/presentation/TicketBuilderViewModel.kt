package zed.rainxch.septemberminichallenges.ticket_builder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TicketBuilderViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TicketBuilderState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadInitialData()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TicketBuilderState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            val tickets = listOf(
                Ticket(
                    title = "Standard",
                    price = 40f,
                    ticketType = TicketType.STANDART
                ),
                Ticket(
                    title = "VIP",
                    price = 70f,
                    ticketType = TicketType.VIP
                ),
                Ticket(
                    title = "Backstage",
                    price = 120f,
                    ticketType = TicketType.BACKSTAGE
                )
            )

            _state.update {
                it.copy(
                    tickets = tickets,
                    selectedTicketType = null,
                    quantity = 1,
                    total = 0f
                )
            }
        }
    }

    fun onAction(action: TicketBuilderAction) {
        when (action) {
            is TicketBuilderAction.OnTicketTypeSelected -> {
                _state.update {
                    it.copy(
                        selectedTicketType = action.ticket,
                        quantity = 1,
                        total = 0f
                    )
                }

                calculateTotal()
            }

            TicketBuilderAction.OnQuantityDecrease -> {
                _state.update { it.copy(quantity = it.quantity - 1) }

                calculateTotal()
            }

            TicketBuilderAction.OnQuantityIncrease -> {
                _state.update { it.copy(quantity = it.quantity + 1) }

                calculateTotal()
            }
        }
    }

    private fun calculateTotal() {
        viewModelScope.launch {
            val state = _state.value
            state.selectedTicketType?.let { it1 ->
                _state.update {
                    it.copy(
                        total = it1.price * state.quantity
                    )
                }
            }
        }
    }

}


fun Float.toFormattedValue(): String {
    return this.toString().dropLast(2)
}
