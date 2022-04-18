package com.devcommop.joaquin.crptoola.presentation.coin_detail

import com.devcommop.joaquin.crptoola.domain.model.Coin
import com.devcommop.joaquin.crptoola.domain.model.CoinDetail

data class CoinDetailState(//defines the initial state of our app
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
