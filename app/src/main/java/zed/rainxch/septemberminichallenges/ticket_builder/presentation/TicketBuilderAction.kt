package zed.rainxch.septemberminichallenges.ticket_builder.presentation

sealed interface TicketBuilderAction {
    data class OnTicketTypeSelected(val ticket: Ticket) : TicketBuilderAction
    data object OnQuantityIncrease : TicketBuilderAction
    data object OnQuantityDecrease : TicketBuilderAction
}