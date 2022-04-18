package com.devcommop.joaquin.crptoola.data.repository
import com.devcommop.joaquin.crptoola.data.remote.CoinPaprikaApi
import com.devcommop.joaquin.crptoola.data.remote.dto.CoinDetailDto
import com.devcommop.joaquin.crptoola.data.remote.dto.CoinDto
import com.devcommop.joaquin.crptoola.domain.repository.CoinRepository
import javax.inject.Inject

//this class means CoinRepositoryImplementation
class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}