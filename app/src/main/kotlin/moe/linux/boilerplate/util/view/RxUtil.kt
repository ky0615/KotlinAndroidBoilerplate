package moe.linux.boilerplate.util.view

import io.reactivex.Observable

fun usingProgress(start: () -> Unit = {}, finish: (Unit) -> Unit = {})
    = Observable.using(start, { Observable.just(Unit) }, finish)
