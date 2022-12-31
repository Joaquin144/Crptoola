package com.devcommop.joaquin.crptoola.domain.repository

import com.devcommop.joaquin.crptoola.data.remote.dto.CoinDetailDto
import com.devcommop.joaquin.crptoola.data.remote.dto.CoinDto

/* why we create 2 repos in domain & data package ?
Answer :--- 2 major reasons
1.) domain should not know about data. But data can know about domain. Here we define what ops we perform (get/push/delete etc from server , local DB caching etc.). The data layer would take care of how to perform them. Actual implementation means direct access to data and we dont want domain layer to access data for our ease of coding and maintenance(replace broken parts)
2.) The benefit of doing so is in Unit Testing. for example if we want to test getCoinById function we can do that by localHost testing rather than with actual server as our API use quota would exceed its limit and our expenses would go up. Hum fake repository bana lenge jo data wali repository ko simulate karegi aur Unit Testing kar sakte hain. eg: bhot saari companies requestMyData feature deti hain users ko par aksar yeh process 3-7 days long hota hai. In such a case for a developer who is working on this feature if he has to test it then he would not have to wait 3-7 days long rather he can make a fake repository on his local machine and test it.🔥🔥
 */
interface CoinRepository {
    suspend fun getCoins() : List<CoinDto>
    suspend fun getCoinById(coinId: String) : CoinDetailDto
}