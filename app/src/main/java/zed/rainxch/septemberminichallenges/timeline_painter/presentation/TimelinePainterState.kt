package zed.rainxch.septemberminichallenges.timeline_painter.presentation

import zed.rainxch.septemberminichallenges.timeline_painter.presentation.model.Genre
import zed.rainxch.septemberminichallenges.timeline_painter.presentation.model.Timeline

data class TimelinePainterState(
    val zoomFraction: Float = 1f,
    val scrollOffsetX: Float = 0f,
    val scrollOffsetY: Float = 0f,
    val genres: List<Genre> = emptyList(),
    val timelines: List<Timeline> = emptyList(),
    val showHint: Boolean = true,
    val times: List<String> = emptyList(),
) {
    val formattedZoomFraction
        get() = "${zoomFraction * 100}%"
}