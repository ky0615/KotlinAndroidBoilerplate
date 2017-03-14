package moe.linux.boilerplate.api

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class AbstructApiClient {
    protected fun <T : Any?> bindThread(single: Single<T>): Single<T> =
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
