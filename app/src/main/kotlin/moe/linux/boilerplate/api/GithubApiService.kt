package moe.linux.boilerplate.api

import moe.linux.boilerplate.api.model.CommitsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubApiService {

    @GET("/repos/{repo}/commits")
    fun showCommitsList(@Path("repo") repo: String): Observable<List<CommitsResponse>>
}
