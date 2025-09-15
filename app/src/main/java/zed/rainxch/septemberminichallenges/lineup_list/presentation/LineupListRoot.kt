package zed.rainxch.septemberminichallenges.lineup_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.lineup_list.presentation.components.StageItem
import zed.rainxch.septemberminichallenges.ui.theme.LineupListColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

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
    Scaffold(
        containerColor = LineupListColors.surface,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp, start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "Festival\n" +
                        "Lineup",
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 60.sp,
                color = LineupListColors.textPrimary,
                lineHeight = 60.sp * .9f
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Tap a stage to view performers",
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = LineupListColors.textSecondary
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = state.stages,
                    key = { it.title }
                ) { stage ->
                    StageItem(
                        stage = stage,
                        onItemClick = {
                            onAction(LineupListAction.OnStageClick(stage))
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LineupListScreen(
        state = LineupListState(),
        onAction = {}
    )
}