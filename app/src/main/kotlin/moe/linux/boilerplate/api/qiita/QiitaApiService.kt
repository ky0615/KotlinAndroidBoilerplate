package moe.linux.boilerplate.api.qiita

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface QiitaApiService {

    @GET("users/{user}/stocks")
    fun stockList(
        @Path("user") user: String
    ): Observable<List<StockListResponse>>
}