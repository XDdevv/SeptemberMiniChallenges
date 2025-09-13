package zed.rainxch.septemberminichallenges.lineup_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.exp

class LineupListViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LineupListState())
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
            initialValue = LineupListState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            val stages = listOf(
                Stage(
                    title = "Stage A",
                    expanded = false,
                    items = listOf(
                        "Morning Bloom" to "11:00",
                        "Synth River" to "12:30",
                    )
                ),
                Stage(
                    title = "Stage B",
                    expanded = false,
                    items = listOf(
                        "The Suntones" to "13:00",
                        "Blue Voltage" to "14:15",
                        "Midnight Echo" to "15:30",
                    )
                ),
                Stage(
                    title = "Stage C",
                    expanded = false,
                    items = listOf(
                        "Echo Machine" to "16:00",
                        "The 1975 " to "17:15",
                    )
                )
            )

            _state.update { it.copy(stages) }
        }
    }

    fun onAction(action: LineupListAction) {
        when (action) {
            is LineupListAction.OnStageClick -> {
                _state.update { state ->
                    state.copy(
                        stages = state.stages.map { stage ->
                            if (stage.title == action.stage.title) {
                                stage.copy(expanded = true)
                            } else stage.copy(expanded = false)
                        }
                    )
                }
            }
        }
    }

}