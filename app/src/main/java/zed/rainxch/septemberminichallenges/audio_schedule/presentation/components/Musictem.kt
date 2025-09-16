package zed.rainxch.septemberminichallenges.audio_schedule.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.septemberminichallenges.audio_schedule.presentation.Music
import zed.rainxch.septemberminichallenges.ui.theme.AudioScheduleColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun Musictem(
    item: Music,
    modifier: Modifier = Modifier,
) {
    var measurementLayout: TextLayoutResult? by remember { mutableStateOf(null) }
    var isContentTooLong by remember { mutableStateOf(false) }
    val timeNameText = "${item.time} • ${item.name}"

    // Create the full text that would be used in marquee
    val fullMarqueeText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(item.artist)
        }
        append("                        ") // 24dp spacing
        withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
            append(timeNameText)
        }
    }

    LaunchedEffect(measurementLayout) {
        isContentTooLong = (measurementLayout?.lineCount ?: 1) > 1
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(AudioScheduleColors.surfaceHigher)
            .semantics {
                contentDescription = "${item.artist}, ${item.time}, ${item.name}"
                role = Role.Tab
            }
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        // Type badge
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(item.type.color)
                .padding(vertical = 6.dp, horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.type.name.lowercase().replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                fontFamily = parkinsansFont,
                color = AudioScheduleColors.textPrimary
            )
        }

        Spacer(Modifier.height(20.dp))

        // Hidden text to measure the full content - this determines our layout
        Text(
            text = fullMarqueeText,
            fontSize = 19.sp,
            fontFamily = parkinsansFont,
            onTextLayout = { measurementLayout = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp) // Hidden but still measured
                .alpha(0f),
            maxLines = Int.MAX_VALUE // Allow it to wrap for measurement
        )

        if (isContentTooLong) {
            // Marquee when content is too long
            Text(
                text = fullMarqueeText,
                fontSize = 19.sp,
                fontFamily = parkinsansFont,
                color = AudioScheduleColors.textPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .basicMarquee(),
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        } else {
            // Side by side when there's space
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.artist,
                    fontWeight = FontWeight.Normal,
                    fontSize = 19.sp,
                    fontFamily = parkinsansFont,
                    color = AudioScheduleColors.textPrimary,
                    modifier = Modifier.weight(1f, fill = false)
                )

                Text(
                    text = timeNameText,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp,
                    fontFamily = parkinsansFont,
                    color = AudioScheduleColors.textPrimary,
                    textAlign = TextAlign.End
                )
            }
        }

        // Description
        Text(
            text = item.description,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = parkinsansFont,
            color = AudioScheduleColors.textSecondary,
        )
    }
}