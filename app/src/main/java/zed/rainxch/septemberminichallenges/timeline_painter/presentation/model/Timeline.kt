package zed.rainxch.septemberminichallenges.timeline_painter.presentation.model

import androidx.compose.ui.graphics.Color

data class Timeline(
    val startTime: String,
    val durationHours: Double,
    val durationTimeFormatted: String,
    val author: String,
    val background: Color,
    val genre: Genre,
)
