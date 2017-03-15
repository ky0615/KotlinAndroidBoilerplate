package moe.linux.boilerplate.api.github

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("/repos/{author}/{repo}/commits")
    fun showCommitsList(
        @Path("author") author: String,
        @Path("repo") repo: String
    ): Observable<List<CommitsResponse>>
}
