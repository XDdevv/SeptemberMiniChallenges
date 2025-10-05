package zed.rainxch.septemberminichallenges.timeline_painter.presentation

sealed interface TimelinePainterAction {
    data class OnPinch(val fraction: Float) : TimelinePainterAction
    data class OnScroll(
        val scrollOffsetX: Float,
        val scrollOffsetY: Float,
    ) : TimelinePainterAction
}