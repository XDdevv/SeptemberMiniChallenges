package zed.rainxch.septemberminichallenges.lineup_list.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.septemberminichallenges.R
import zed.rainxch.septemberminichallenges.lineup_list.presentation.Stage
import zed.rainxch.septemberminichallenges.ui.theme.LineupListColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun StageItem(
    stage: Stage,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(stage.getContainerColor())
            .clickable(onClick = onItemClick)
            .animateContentSize()
            .padding(horizontal = 12.dp, vertical = 40.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stage.title,
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 38.sp,
                color = LineupListColors.textPrimary,
                lineHeight = 38.sp * .9f
            )

            Icon(
                imageVector = if (stage.expanded) {
                    ImageVector.vectorResource(R.drawable.ic_minus)
                } else Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }

        if (stage.expanded) {
            Spacer(Modifier.height(40.dp))

            stage.items.forEachIndexed { index, item ->
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.first,
                        fontFamily = parkinsansFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = LineupListColors.textPrimary,
                        lineHeight = 24.sp * .9f
                    )

                    Text(
                        text = item.second,
                        fontFamily = parkinsansFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = LineupListColors.textPrimary,
                        lineHeight = 24.sp * .9f
                    )
                }

                if (index != stage.items.lastIndex) {
                    Spacer(Modifier.height(18.dp))

                    HorizontalDivider(
                        thickness = 2.dp,
                        color = LineupListColors.textPrimary
                    )

                    Spacer(Modifier.height(18.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun StageItemPreview() {
    StageItem(
        stage = Stage(
            title = "Stage A",
            expanded = true,
            items = listOf(
                "The Suntones" to "13:00",
                "The Suntones" to "13:00",
            )
        ),
        {}
    )
}
@Preview
@Composable
private fun StageItem2Preview() {
    StageItem(
        stage = Stage(
            title = "Stage A",
            expanded = false,
            items = listOf(
                "The Suntones" to "13:00",
                "The Suntones" to "13:00",
            )
        ),
        {}
    )
}