package zed.rainxch.septemberminichallenges.audio_schedule.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.audio_schedule.presentation.components.Musictem
import zed.rainxch.septemberminichallenges.ui.theme.AudioScheduleColors
import zed.rainxch.septemberminichallenges.ui.theme.TicketBuilderColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun AudioScheduleRoot(
    viewModel: AudioScheduleViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AudioScheduleScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun AudioScheduleScreen(
    state: AudioScheduleState,
    onAction: (AudioScheduleAction) -> Unit,
) {
    Scaffold(
        containerColor = AudioScheduleColors.surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp)
        ) {
            Text(
                text = "Schedule",
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 60.sp,
                color = TicketBuilderColors.textPrimary,
                lineHeight = 60.sp * .9f,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.padding(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.musics) { music ->
                    Musictem(
                        item = music
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AudioScheduleScreen(
        state = AudioScheduleState(),
        onAction = {}
    )
}