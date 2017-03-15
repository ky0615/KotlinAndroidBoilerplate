package moe.linux.boilerplate.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class AbstructApiClient {
    protected fun <T : Any?> bindThread(single: Observable<T>): Observable<T> =
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
