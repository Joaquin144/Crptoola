package com.devcommop.joaquin.crptoola.data.remote.dto

import com.devcommop.joaquin.crptoola.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,//initially this was is_active but android studio nahi manega 😢
    @SerializedName("is_new")
    val isNew: Boolean,
    //val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}