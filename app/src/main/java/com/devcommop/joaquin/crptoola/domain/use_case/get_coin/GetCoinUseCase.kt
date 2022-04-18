package com.devcommop.joaquin.crptoola.domain.use_case.get_coin

import com.devcommop.joaquin.crptoola.common.Resource
import com.devcommop.joaquin.crptoola.data.remote.dto.toCoinDetail
import com.devcommop.joaquin.crptoola.domain.model.CoinDetail
import com.devcommop.joaquin.crptoola.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository //We don't pass CoinRepositoryImpl rather just an interface so that it could be easily replacable
) {
    //Flow tab use karte hain jab ek function time vary karne par different types ki values ko emit karega. Eg: start mei loading phir success ya error and phir user ne kuch change kiya toh dobara loading phir success ya error
    operator fun invoke(coinId: String) : Flow<Resource<CoinDetail>> = flow {
        try{
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()//E! => why this : yeh toh khaali hai 😑
            emit(Resource.Success(coin))
        }catch (e: HttpException){//response of server isn't a success (success => codestarts with 2)
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e : IOException){//when repo can't talk to server eg no internet connection or server is offline
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your Internet connection"))
        }
    }
}