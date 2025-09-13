package zed.rainxch.septemberminichallenges.lineup_list.presentation

data class LineupListState(
    val stages: List<Stage> = emptyList(),
)

data class Stage(
    val title: String,
    val expanded: Boolean,
    val items: List<Pair<String, String>>,
)