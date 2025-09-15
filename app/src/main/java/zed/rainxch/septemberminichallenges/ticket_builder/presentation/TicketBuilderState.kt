package zed.rainxch.septemberminichallenges.ticket_builder.presentation

data class TicketBuilderState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)