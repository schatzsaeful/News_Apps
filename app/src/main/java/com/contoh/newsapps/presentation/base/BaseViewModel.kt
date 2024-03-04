package com.contoh.newsapps.presentation.base

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KProperty1

open class BaseViewModel<S : MavericksState>(
    initialState: S
) : MavericksViewModel<S>(initialState) {

    fun <T : Any?> (suspend () -> T).executeOnIo(
        retainValue: KProperty1<S, Async<T>>? = null,
        reducer: S.(Async<T>) -> S
    ): Job {
        return this.execute(Dispatchers.IO, retainValue, reducer)
    }

    protected open fun <T> Flow<T>.executeOnIo(
        retainValue: KProperty1<S, Async<T>>? = null,
        reducer: S.(Async<T>) -> S
    ): Job {
        return this.execute(Dispatchers.IO, retainValue, reducer)
    }
}
