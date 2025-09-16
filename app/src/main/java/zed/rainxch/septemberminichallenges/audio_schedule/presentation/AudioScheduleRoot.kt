package zed.rainxch.septemberminichallenges.audio_schedule.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AudioScheduleRoot(
    viewModel: AudioScheduleViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AudioScheduleScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AudioScheduleScreen(
    state: AudioScheduleState,
    onAction: (AudioScheduleAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    AudioScheduleScreen(
        state = AudioScheduleState(),
        onAction = {}
    )
}