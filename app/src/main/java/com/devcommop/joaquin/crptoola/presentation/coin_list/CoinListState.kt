package com.devcommop.joaquin.crptoola.presentation.coin_list

import com.devcommop.joaquin.crptoola.domain.model.Coin

data class CoinListState(//defines the initial state of our app
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
