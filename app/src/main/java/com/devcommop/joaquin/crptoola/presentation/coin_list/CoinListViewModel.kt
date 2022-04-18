package com.devcommop.joaquin.crptoola.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcommop.joaquin.crptoola.common.Resource
import com.devcommop.joaquin.crptoola.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private val _state = mutableStateOf(CoinListState()) //ye private hai--> isko ViewModels access karenge
    val state: State<CoinListState> = _state    //ye public hai --> isko Composable access karnge
    /*Ques: Why we are keeping 2 states one for viewmodel and other for composbale though they have same data ?
    ans:-- because we just want to ensure composables don't accidentally chnage it. As presentation layer is dumb about domain layer. Its just a precaution and not some hard and fast rule
     */

    init{
        getCoins()
    }

    private fun getCoins(){
        //onEach ---> on each flow that getCoinsUseCase is emitting perfrom ops inside the block
        getCoinsUseCase().onEach { result->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}