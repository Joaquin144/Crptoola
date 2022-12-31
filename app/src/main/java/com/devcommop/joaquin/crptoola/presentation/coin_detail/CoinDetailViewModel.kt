package com.devcommop.joaquin.crptoola.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.joaquin.crptoola.common.Constants.PARAM_COIN_ID
import com.devcommop.joaquin.crptoola.common.Resource
import com.devcommop.joaquin.crptoola.domain.use_case.get_coin.GetCoinUseCase
import com.devcommop.joaquin.crptoola.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    val savedStateHandle: SavedStateHandle//E! is it replacement of Parcelizable ?
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState()) //ye private hai--> isko ViewModels access karenge
    val state: State<CoinDetailState> = _state    //ye public hai --> isko Composable access karnge
    /*Ques: Why we are keeping 2 states one for viewmodel and other for composbale though they have same data ?
    ans:-- because we just want to ensure composables don't accidentally chnage it. As presentation layer is dumb about domain layer. Its just a precaution and not some hard and fast rule
     */

    init{//The below line is automatically taking in arguments that were passed while compose navigation, since those arguments get passed in savedStateHandle. E! --> Is this cause of coupling bw VM and ui ?
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let{ coinId ->
            getCoin(coinId = coinId)
        }
    }

    private fun getCoin(coinId : String){
        //onEach ---> on each flow that getCoinsUseCase is emitting perfrom ops inside the block
        getCoinUseCase(coinId).onEach { result->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}