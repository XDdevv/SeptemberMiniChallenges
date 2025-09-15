package zed.rainxch.septemberminichallenges.ticket_builder.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zed.rainxch.septemberminichallenges.R
import zed.rainxch.septemberminichallenges.ui.theme.LineupListColors
import zed.rainxch.septemberminichallenges.ui.theme.TicketBuilderColors
import zed.rainxch.septemberminichallenges.ui.theme.parkinsansFont

@Composable
fun TicketBuilderRoot(
    viewModel: TicketBuilderViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TicketBuilderScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun TicketBuilderScreen(
    state: TicketBuilderState,
    onAction: (TicketBuilderAction) -> Unit,
) {
    Scaffold(
        containerColor = TicketBuilderColors.surface,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 28.dp)
        ) {
            Text(
                text = "Ticket\nBuilder",
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 60.sp,
                color = TicketBuilderColors.textPrimary,
                lineHeight = 60.sp * .9f,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Select Ticket Type & Quantity",
                fontFamily = parkinsansFont,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = TicketBuilderColors.textSecondary,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )

            Spacer(Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        color = TicketBuilderColors.surfaceHigher,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 40.dp)
            ) {
                Text(
                    text = "Ticket Type:",
                    fontFamily = parkinsansFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = TicketBuilderColors.textSecondary
                )

                Spacer(Modifier.height(28.dp))

                LazyColumn {
                    items(state.tickets) { ticket ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = state.selectedTicketType == ticket,
                                    onClick = {
                                        onAction(TicketBuilderAction.OnTicketTypeSelected(ticket))
                                    },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = TicketBuilderColors.textPrimary,
                                        unselectedColor = TicketBuilderColors.textPrimary,
                                    )
                                )

                                Text(
                                    text = ticket.title,
                                    fontFamily = parkinsansFont,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 24.sp,
                                    color = TicketBuilderColors.textPrimary
                                )
                            }

                            Text(
                                text = "$${ticket.price}",
                                fontFamily = parkinsansFont,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                color = TicketBuilderColors.textPrimary
                            )
                        }
                    }
                }

                Spacer(Modifier.height(48.dp))

                Text(
                    text = "Quantity:",
                    fontFamily = parkinsansFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = TicketBuilderColors.textSecondary
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            onAction(TicketBuilderAction.OnQuantityDecrease)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            disabledContentColor = TicketBuilderColors.textDisabled,
                        ),
                        modifier = Modifier
                            .background(
                                color = if (state.decreaseEnabled) {
                                    TicketBuilderColors.surface
                                } else Color.Transparent,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(4.dp),
                        enabled = state.decreaseEnabled
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_minus),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Text(
                        text = "${state.quantity}",
                        fontFamily = parkinsansFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 32.sp,
                        color = TicketBuilderColors.textPrimary
                    )

                    IconButton(
                        onClick = {
                            onAction(TicketBuilderAction.OnQuantityIncrease)
                        },
                        modifier = Modifier
                            .background(TicketBuilderColors.surface, RoundedCornerShape(12.dp))
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }

                Spacer(Modifier.height(48.dp))

                HorizontalDivider(
                    thickness = 2.dp,
                    color = TicketBuilderColors.textPrimary
                )

                Spacer(Modifier.height(40.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total:",
                        fontFamily = parkinsansFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 28.sp,
                        color = TicketBuilderColors.textPrimary
                    )

                    Text(
                        text = "$${state.total}",
                        fontFamily = parkinsansFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        color = TicketBuilderColors.textPrimary
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = TicketBuilderColors.lime,
                    contentColor = TicketBuilderColors.textPrimary,
                    disabledContainerColor = TicketBuilderColors.surfaceHigher,
                    disabledContentColor = TicketBuilderColors.textDisabled,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(
                    text = "Purchase",
                    fontFamily = parkinsansFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    TicketBuilderScreen(
        state = TicketBuilderState(),
        onAction = {}
    )
}