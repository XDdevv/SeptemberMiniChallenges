package zed.rainxch.septemberminichallenges.festival_map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.septemberminichallenges.R
import zed.rainxch.septemberminichallenges.ui.theme.FestivalMapColors

class FestivalMapViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(FestivalMapState())
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
            initialValue = FestivalMapState()
        )

    private fun loadInitialData() {
        viewModelScope.launch {
            val filters = listOf(
                Filter(
                    id = 0,
                    iconRes = R.drawable.ic_stage,
                    title = "Stages",
                    background = FestivalMapColors.lime,
                    selected = false
                ),
                Filter(
                    id = 1,
                    iconRes = R.drawable.ic_food,
                    title = "Food",
                    background = FestivalMapColors.pink,
                    selected = false
                ),
                Filter(
                    id = 2,
                    iconRes = R.drawable.ic_wc,
                    title = "WC",
                    background = FestivalMapColors.orange,
                    selected = false
                ),
            )

            val items = listOf(
                Item(filterId = 0, itemImgRes = R.drawable.indc_stages),
                Item(filterId = 1, itemImgRes = R.drawable.indc_food),
                Item(filterId = 2, itemImgRes = R.drawable.indc_wc),
            )

            _state.update { state ->
                state.copy(
                    filters = filters,
                    items = items
                )
            }
        }
    }

    fun onAction(action: FestivalMapAction) {
        when (action) {
            is FestivalMapAction.OnFilterToggle -> {
                toggleFilter(action.filter)
            }
        }
    }

    private fun toggleFilter(selectedItem: Filter) {
        _state.update {
            it.copy(
                filters = it.filters.map { filter ->
                    if (filter.id == selectedItem.id) {
                        filter.copy(selected = !filter.selected)
                    } else filter
                }
            )
        }
    }

}