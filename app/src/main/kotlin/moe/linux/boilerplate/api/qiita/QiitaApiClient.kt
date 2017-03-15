package moe.linux.boilerplate.api.qiita

import io.reactivex.Observable
import moe.linux.boilerplate.api.AbstructApiClient
import javax.inject.Inject

class QiitaApiClient @Inject constructor(private val qiitaApiService: QiitaApiService) : AbstructApiClient() {
    fun stockList(user: String): Observable<List<StockListResponse>> {
        return bindThread(qiitaApiService.stockList(user))
    }
}