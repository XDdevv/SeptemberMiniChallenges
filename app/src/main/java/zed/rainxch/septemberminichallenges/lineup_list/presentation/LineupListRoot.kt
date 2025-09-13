package zed.rainxch.septemberminichallenges.lineup_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.ui.theme.SeptemberMiniChallengesTheme

@Composable
fun LineupListRoot(
    viewModel: LineupListViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LineupListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun LineupListScreen(
    state: LineupListState,
    onAction: (LineupListAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    SeptemberMiniChallengesTheme {
        LineupListScreen(
            state = LineupListState(),
            onAction = {}
        )
    }
}