package zed.rainxch.septemberminichallenges.festival_map.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FestivalMapRoot(
    viewModel: FestivalMapViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FestivalMapScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun FestivalMapScreen(
    state: FestivalMapState,
    onAction: (FestivalMapAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    FestivalMapScreen(
        state = FestivalMapState(),
        onAction = {}
    )
}