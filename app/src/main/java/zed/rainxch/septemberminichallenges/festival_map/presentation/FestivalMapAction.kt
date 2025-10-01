package zed.rainxch.septemberminichallenges.festival_map.presentation

sealed interface FestivalMapAction {
    data class OnFilterToggle(val filter: Filter) : FestivalMapAction
}