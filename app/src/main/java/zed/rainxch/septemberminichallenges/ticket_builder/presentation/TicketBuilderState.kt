package zed.rainxch.septemberminichallenges.ticket_builder.presentation

data class TicketBuilderState(
    val tickets: List<Ticket> = emptyList(),
    val selectedTicketType: Ticket? = null,
    val quantity: Int = 1,
    val total: Float = 0f,
) {
    val purchageEnabled: Boolean
        get() = selectedTicketType != null
}

data class Ticket(
    val title: String,
    val price: Float,
    val ticketType: TicketType,
)

enum class TicketType {
    STANDART,
    VIP,
    BACKSTAGE
}