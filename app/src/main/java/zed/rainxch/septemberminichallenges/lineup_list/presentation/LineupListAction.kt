package zed.rainxch.septemberminichallenges.lineup_list.presentation

sealed interface LineupListAction {
    data class OnStageClick(val stage: Stage) : LineupListAction
}