package zed.rainxch.septemberminichallenges.audio_schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zed.rainxch.septemberminichallenges.audio_schedule.presentation.components.Musictem

class AudioScheduleViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(AudioScheduleState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                initializeData()

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AudioScheduleState()
        )

    private fun initializeData() {
        viewModelScope.launch {
            val musics = listOf(
                Music(
                    type = MysicType.Indie,
                    artist = "Bon Iver",
                    time = "15:30",
                    name = "Main Stage",
                    description = "Atmospheric folk-electronic set to start the day."
                ),
                Music(
                    type = MysicType.Electronic,
                    artist = "Jamie xx",
                    time = "16:00",
                    name = "Electro Stage",
                    description = "A genre-bending solo set with deep bass and light textures."
                ),
                Music(
                    type = MysicType.Headliner,
                    artist = "Florence + The Machine",
                    time = "17:00",
                    name = "Main Stage",
                    description = "Special acoustic set this evening only."
                ),
                Music(
                    type = MysicType.Indie,
                    artist = "The National",
                    time = "18:00",
                    name = "Sunset Stage",
                    description = "Known for their emotional rock anthems."
                ),
                Music(
                    type = MysicType.Experimental,
                    artist = "Björk",
                    time = "18:30",
                    name = "Electro Stage",
                    description = "Avant-garde visual and vocal per formance."
                ),
                Music(
                    type = MysicType.Indie,
                    artist = "Tame Impala",
                    time = "19:00",
                    name = "Sunset Stage",
                    description = "Celebrated psychedelic show from Australia."
                ),
                Music(
                    type = MysicType.Electronic,
                    artist = "The Chemical Brothers",
                    time = "20:15",
                    name = "Electro Stage",
                    description = "High-energy visuals with legendary electronica."
                ),
                Music(
                    type = MysicType.Headliner,
                    artist = "Foo Fighters",
                    time = "21:00",
                    name = "Main Stage",
                    description = "Classic stadium rock at its finest."
                ),
                Music(
                    type = MysicType.AltRock,
                    artist = "Arctic Monkeys",
                    time = "22:00",
                    name = "Sunset Stage",
                    description = "Charismatic blend of indie rock and post-punk revival."
                ),
                Music(
                    type = MysicType.Headliner,
                    artist = "Radiohead",
                    time = "23:00",
                    name = "Main Stage",
                    description = "Returning to the stage wit h a new album."
                ),
            )

            _state.update { it.copy(musics = musics) }
        }
    }

    fun onAction(action: AudioScheduleAction) {
        when (action) {
            else -> { }
        }
    }

}