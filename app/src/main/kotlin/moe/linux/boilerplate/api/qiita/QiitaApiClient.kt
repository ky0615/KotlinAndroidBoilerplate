package moe.linux.boilerplate.api.qiita

import io.reactivex.Single
import moe.linux.boilerplate.api.AbstructApiClient
import javax.inject.Inject

class QiitaApiClient @Inject constructor(val qiitaApiService: QiitaApiService) : AbstructApiClient() {
    fun stockList(user: String): Single<List<StockListResponse>> {
        return bindThread(qiitaApiService.stockList(user))
    }
}