package zed.rainxch.septemberminichallenges.timeline_painter.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TimelinePainterRoot(
    viewModel: TimelinePainterViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TimelinePainterScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TimelinePainterScreen(
    state: TimelinePainterState,
    onAction: (TimelinePainterAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    TimelinePainterScreen(
        state = TimelinePainterState(),
        onAction = {}
    )
}