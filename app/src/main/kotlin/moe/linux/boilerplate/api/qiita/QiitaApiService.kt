package moe.linux.boilerplate.api.qiita

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface QiitaApiService {

    @GET("users/{user}/stocks")
    fun stockList(
        @Path("user") user: String
    ): Single<List<StockListResponse>>
}