package zed.rainxch.septemberminichallenges.festival_map.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zed.rainxch.septemberminichallenges.festival_map.presentation.Filter
import zed.rainxch.septemberminichallenges.ui.theme.FestivalMapColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun FilterItem(
    filter: Filter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(if (filter.selected) filter.background else Color.Transparent)
            .border(
                width = 2.dp,
                color = if (filter.selected) {
                    filter.background
                } else FestivalMapColors.textSecondary,
                shape = CircleShape
            )
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(filter.iconRes),
            contentDescription = filter.title,
            modifier = Modifier.size(16.dp),
            tint = if (filter.selected) {
                FestivalMapColors.textPrimary
            } else FestivalMapColors.textPrimary
        )

        Text(
            text = filter.title,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontFamily = parkinsansFont,
            color = if (filter.selected) {
                FestivalMapColors.textPrimary
            } else FestivalMapColors.textPrimary
        )
    }
}