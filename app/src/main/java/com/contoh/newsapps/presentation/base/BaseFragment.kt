package com.contoh.newsapps.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.DeliveryMode
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.RedeliverOnStart
import com.airbnb.mvrx.Success
import com.contoh.newsapps.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

abstract class BaseFragment(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes), MavericksView {

    private var progressView: View? = null

    override fun onPause() {
        showFullScreenLoading(false)
        super.onPause()
    }

    protected fun showFullScreenLoading(isLoading: Boolean) {
        val viewGroup = requireActivity().window.decorView as ViewGroup
        if (progressView != null) {
            viewGroup.removeView(progressView)
        } else {
            progressView = LayoutInflater
                .from(requireActivity())
                .inflate(R.layout.common_dialog_loading_screen, viewGroup, false)
        }
        if (isLoading) viewGroup.addView(progressView)
        else {
            viewGroup.removeView(progressView)
            progressView = null
        }
    }

    protected fun safeRun(
        delayTime: Long = 0L,
        block: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycle.coroutineScope.launch(Dispatchers.Main) {
            delay(delayTime)
            block.invoke()
        }
    }

    protected fun ioRun(
        delayTime: Long = 0L,
        block: suspend () -> Unit
    ) {
        viewLifecycleOwner.lifecycle.coroutineScope.launch(Dispatchers.IO) {
            delay(delayTime)
            block.invoke()
        }
    }

    protected fun <S : MavericksState, T> MavericksViewModel<S>.onCommonAsync(
        asyncProp: KProperty1<S, Async<T>>,
        deliveryMode: DeliveryMode = RedeliverOnStart,
        onFail: (suspend (Throwable) -> Unit)? = null,
        onSuccess: (suspend (T) -> Unit)? = null
    ) {
        this.onEach(asyncProp, deliveryMode) {
            when (it) {
                is Loading -> showFullScreenLoading(true)
                is Success -> {
                    showFullScreenLoading(false)
                    onSuccess?.invoke(it.invoke())
                }
                is Fail -> {
                    showFullScreenLoading(false)
                    onFail?.invoke(it.error)
                }
                else -> Unit
            }
        }
    }
}
