package zed.rainxch.septemberminichallenges.audio_schedule.presentation

import androidx.compose.ui.graphics.Color
import zed.rainxch.septemberminichallenges.ui.theme.AudioScheduleColors

data class AudioScheduleState(
    val musics: List<Music> = emptyList(),
)

data class Music(
    val type: MysicType,
    val artist: String,
    val time: String,
    val name: String,
    val description: String,
)

enum class MysicType(
    val color: Color,
) {
    Indie(AudioScheduleColors.orange),
    Electronic(AudioScheduleColors.lime),
    Headliner(AudioScheduleColors.purple),
    Experimental(AudioScheduleColors.pink),
    AltRock(AudioScheduleColors.blue),
}