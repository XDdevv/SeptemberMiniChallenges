package zed.rainxch.septemberminichallenges.lineup_list.presentation

import androidx.compose.ui.graphics.Color
import zed.rainxch.septemberminichallenges.ui.theme.LineupListColors

data class LineupListState(
    val stages: List<Stage> = emptyList(),
)

data class Stage(
    val title: String,
    val expanded: Boolean,
    val items: List<Pair<String, String>>,
) {
    fun getContainerColor(): Color {
        return when (title) {
            "Stage A" -> LineupListColors.lime
            "Stage B" -> LineupListColors.orange
            "Stage C" -> LineupListColors.pink
            else -> LineupListColors.lime
        }
    }
}