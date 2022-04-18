package com.devcommop.joaquin.crptoola.domain.model

data class Coin(
    val id: String,
    val isActive: Boolean,//initially this was is_active but android studio nahi manega 😢
    val name: String,
    val rank: Int,
    val symbol: String,
)
