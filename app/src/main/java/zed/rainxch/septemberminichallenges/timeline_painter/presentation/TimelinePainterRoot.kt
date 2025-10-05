package zed.rainxch.septemberminichallenges.timeline_painter.presentation

import androidx.compose.foundation.gestures.rememberScrollable2DState
import androidx.compose.foundation.gestures.scrollable2D
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.timeline_painter.presentation.utils.Constants
import zed.rainxch.septemberminichallenges.ui.theme.TimelinePainterColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun TimelinePainterRoot(
    viewModel: TimelinePainterViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TimelinePainterScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TimelinePainterScreen(
    state: TimelinePainterState,
    onAction: (TimelinePainterAction) -> Unit,
) {
    val density = LocalDensity.current

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(12.dp)
            ) {
                Text(
                    text = "Festival Schedule",
                    fontWeight = FontWeight.Medium,
                    color = TimelinePainterColors.textSecondary,
                    fontSize = 20.sp,
                    fontFamily = parkinsansFont,
                )

                Spacer(Modifier.height(16.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(state.genres) { genre ->
                        Text(
                            text = genre.title,
                            color = TimelinePainterColors.textPrimary,
                            fontSize = 24.sp * state.zoomFraction,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = parkinsansFont,
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 14.5.dp)
                        )
                    }
                }

            }
        },
        containerColor = TimelinePainterColors.surface
    ) { innerPadding ->
        val scrollState = rememberScrollable2DState(
            consumeScrollDelta = { delta ->

                delta  // Consume the scroll delta
            }
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .drawWithContent {
                    drawContent()

                    drawRect(
                        color = TimelinePainterColors.textPrimary,
                        size = Size(width = size.width, height = 5f),
                    )
                }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .drawBehind {
                        drawRect(
                            color = TimelinePainterColors.textSecondary,
                            size = Size(width = 1f, height = size.height),
                            topLeft = Offset(x = size.width, y = 0f)
                        )
                    }
                    .padding(top = 20.dp)
                    .padding(horizontal = 12.dp)
            ) {
                items(state.times.filter { it.endsWith("00") }) { time ->
                    Text(
                        text = time,
                        color = TimelinePainterColors.textSecondary,
                        fontSize = 12.sp * state.zoomFraction,
                        fontWeight = FontWeight.Normal,
                        fontFamily = parkinsansFont,
                        modifier = Modifier
                            .height((Constants.CELL_HEIGHT * 2).dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scrollable2D(scrollState)
                    .drawBehind {
                        for (col in 0..3) {
                            val x = (Constants.CELL_WIDTH * 2.5).toFloat() * col.toFloat()
                            drawLine(
                                color = TimelinePainterColors.textSecondary,
                                start = Offset(x, 0f),
                                end = Offset(x, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                        // Draw horizontal lines
                        for (row in 0..state.times.size) {
                            val y = (Constants.CELL_HEIGHT * 2.5).toFloat() * row.toFloat()
                            drawLine(
                                color = TimelinePainterColors.textSecondary,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                    }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TimelinePainterScreen(
        state = TimelinePainterState(),
        onAction = {}
    )
}