package moe.linux.boilerplate.api

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moe.linux.boilerplate.api.model.CommitsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubApiClient @Inject constructor(val githubApiService: GithubApiService) {
    fun showCommitsList(author: String, repo: String): Single<List<CommitsResponse>> {
        return bindThread(githubApiService.showCommitsList(author, repo))
    }

    private fun <T : Any?> bindThread(single: Single<T>): Single<T> =
            single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
}
