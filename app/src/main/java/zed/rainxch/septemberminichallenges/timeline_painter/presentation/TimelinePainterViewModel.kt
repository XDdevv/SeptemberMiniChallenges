package zed.rainxch.septemberminichallenges.timeline_painter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.septemberminichallenges.timeline_painter.presentation.model.Genre
import zed.rainxch.septemberminichallenges.timeline_painter.presentation.model.Timeline
import zed.rainxch.septemberminichallenges.ui.theme.TimelinePainterColors
import kotlin.time.Duration.Companion.seconds

class TimelinePainterViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TimelinePainterState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadInitialData()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TimelinePainterState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            val timelines = listOf(
                Timeline(
                    durationHours = 1.0,
                    durationTimeFormatted = "12:00–13:00",
                    author = "DJ A",
                    background = TimelinePainterColors.lime,
                    genre = Genre.Electro,
                    startTime = "12:00"
                ),
                Timeline(
                    durationHours = 1.5,
                    durationTimeFormatted = "13:00–14:30",
                    author = "Band X",
                    background = TimelinePainterColors.orange,
                    genre = Genre.Main,
                    startTime = "13:00"
                ),
                Timeline(
                    durationHours = 1.0,
                    durationTimeFormatted = "14:00–15:00",
                    author = "RockZ",
                    background = TimelinePainterColors.pink,
                    genre = Genre.Rock,
                    startTime = "14:00"
                ),
                Timeline(
                    durationHours = 1.5,
                    durationTimeFormatted = "15:00–16:30",
                    author = "Ambient Line",
                    background = TimelinePainterColors.lime,
                    genre = Genre.Electro,
                    startTime = "15:00"
                ),

                Timeline(
                    durationHours = 1.5,
                    durationTimeFormatted = "16:30–18:00",
                    author = "Florence + The Machine",
                    background = TimelinePainterColors.orange,
                    genre = Genre.Main,
                    startTime = "16:30"
                ),

                Timeline(
                    durationHours = 1.0,
                    durationTimeFormatted = "17:00–18:00",
                    author = "The National",
                    background = TimelinePainterColors.pink,
                    genre = Genre.Rock,
                    startTime = "17:00"
                ),

                Timeline(
                    durationHours = 1.0,
                    durationTimeFormatted = "18:00–19:00",
                    author = "Jamie xx",
                    background = TimelinePainterColors.lime,
                    genre = Genre.Electro,
                    startTime = "18:00"
                )
            )
            val times = mutableListOf<String>()
            (12..23).forEach {
                times.add("${it}:00")
                times.add("${it}:30")
            }

            _state.update {
                it.copy(
                    timelines = timelines,
                    genres = Genre.entries.toList(),
                    times = times,
                    showHint = true,
                    zoomFraction = 1f
                )
            }

            launch {
                delay((2..3).random().seconds)

                _state.update {
                    it.copy(
                        showHint = false
                    )
                }
            }
        }
    }

    fun onAction(action: TimelinePainterAction) {
        when (action) {
            is TimelinePainterAction.OnPinch -> {
                _state.update {
                    it.copy(
                        zoomFraction = it.zoomFraction + action.fraction
                    )
                }
            }

            is TimelinePainterAction.OnScroll -> {
                _state.update {
                    it.copy(
                        scrollOffsetX = action.scrollOffsetX,
                        scrollOffsetY = action.scrollOffsetY,
                    )
                }
            }
        }
    }

}