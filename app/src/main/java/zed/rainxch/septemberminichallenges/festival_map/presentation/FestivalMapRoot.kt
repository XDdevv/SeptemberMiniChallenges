package zed.rainxch.septemberminichallenges.festival_map.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.R
import zed.rainxch.septemberminichallenges.festival_map.presentation.components.FilterItem
import zed.rainxch.septemberminichallenges.ui.theme.FestivalMapColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun FestivalMapRoot(
    viewModel: FestivalMapViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FestivalMapScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun FestivalMapScreen(
    state: FestivalMapState,
    onAction: (FestivalMapAction) -> Unit,
) {
    Scaffold(
        containerColor = FestivalMapColors.surface,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Festival Map",
                    fontWeight = FontWeight.SemiBold,
                    color = FestivalMapColors.textPrimary,
                    fontFamily = parkinsansFont,
                    fontSize = 30.sp
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.filters) { filter ->
                        FilterItem(
                            filter = filter,
                            onClick = {
                                onAction(FestivalMapAction.OnFilterToggle(filter))
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .drawWithContent {
                    drawContent()

                    drawRect(
                        color = FestivalMapColors.textPrimary,
                        size = Size(width = size.width, height = 5f)
                    )
                }
        ) {
            Image(
                painter = painterResource(R.drawable.map),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            state.items.forEach { item ->
                if (state.filters.find { it.id == item.filterId } != null
                    && state.filters.find { it.id == item.filterId }?.selected == true) {
                    Image(
                        painter = painterResource(item.itemImgRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .animateContentSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FestivalMapScreen(
        state = FestivalMapState(
            filters = listOf(
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
        ),
        onAction = {}
    )
}