package zed.rainxch.septemberminichallenges.ticket_builder.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TicketBuilderRoot(
    viewModel: TicketBuilderViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TicketBuilderScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TicketBuilderScreen(
    state: TicketBuilderState,
    onAction: (TicketBuilderAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    TicketBuilderScreen(
        state = TicketBuilderState(),
        onAction = {}
    )
}