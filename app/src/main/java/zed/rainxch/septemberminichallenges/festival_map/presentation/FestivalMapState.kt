package zed.rainxch.septemberminichallenges.festival_map.presentation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class FestivalMapState(
    val filters: List<Filter> = emptyList(),
    val items: List<Item> = emptyList(),
)

data class Filter(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val title: String,
    val background: Color,
    val selected: Boolean = false,
)

data class Item(
    val filterId: Int,
    @DrawableRes val itemImgRes: Int,
)