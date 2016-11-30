package moe.linux.boilerplate.api

import io.reactivex.Single
import moe.linux.boilerplate.api.model.CommitsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/repos/{author}/{repo}/commits")
    fun showCommitsList(
            @Path("author") author: String,
            @Path("repo") repo: String
    ): Single<List<CommitsResponse>>
}
