package com.devcommop.joaquin.crptoola.presentation.coin_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devcommop.joaquin.crptoola.presentation.Screen
import com.devcommop.joaquin.crptoola.presentation.coin_detail.CoinDetailViewModel
import com.devcommop.joaquin.crptoola.presentation.coin_detail.components.CoinTag
import com.devcommop.joaquin.crptoola.presentation.coin_detail.components.TeamListItem
import com.devcommop.joaquin.crptoola.presentation.coin_list.CoinListViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    //navController: NavController, //-?we don't need navController here because we don't need to navigate anywhere
    viewModel: CoinDetailViewModel = hiltViewModel()//E! this line of code
) {
    val state =  viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        state.coin?.let{ coin ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ){
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Text(
                            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if(coin.isActive) "active" else "inactive",
                            color = if(coin.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coin.description,
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    //FlowRow ka kaam hai ki elements ko next line mei bhej de agar woh scren width exceed karrahe ho
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.h3
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }//we can't put items inside item so we exited item block of lazyColumn. Note that: we are not exiting the lazyColumn itself
                items(coin.team){ teamMember ->
                    TeamListItem(teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()//Divider provides the line (looks clean to user)
                }
            }
        }

        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}