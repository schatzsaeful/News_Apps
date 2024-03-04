package com.contoh.newsapps.utils.ext

import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.ViewModelContext
import com.contoh.newsapps.data.network.exceptions.BadRequestException
import com.contoh.newsapps.data.network.exceptions.InternalServerException
import com.contoh.newsapps.data.network.exceptions.MethodNotAllowedException
import com.contoh.newsapps.data.network.exceptions.NotFoundException
import com.contoh.newsapps.data.network.exceptions.TooManyRequestException
import com.contoh.newsapps.data.network.exceptions.UnauthorizedException
import com.contoh.newsapps.data.network.exceptions.UnprocessableEntityException
import com.contoh.newsapps.data.network.responses.ErrorResponse
import org.koin.android.ext.android.inject
import retrofit2.HttpException

inline fun <T> successOrError(block: () -> T): T {
    return try {
        block.invoke()
    } catch (e: HttpException) {
        val json = e.response()?.errorBody()?.string()
        var errorMessage = json?.safeGenerateModel<ErrorResponse>()
            ?.errors
            .orEmpty()

        if (errorMessage.isBlank()) {
            errorMessage = "Unknown Error"
        }

        when (e.code()) {
            400 -> throw BadRequestException(errorMessage)
            401 -> throw UnauthorizedException(errorMessage)
            404 -> throw NotFoundException(errorMessage)
            405 -> throw MethodNotAllowedException(errorMessage)
            422 -> throw UnprocessableEntityException(errorMessage)
            429 -> throw TooManyRequestException(errorMessage)
            500 -> throw InternalServerException(errorMessage)
            else -> throw e
        }
    } catch (e: Exception) {

        throw e
    }
}

inline fun <T, R> T.mapTo(block: (T) -> R): R {
    return block.invoke(this)
}

inline fun <reified T : Any> ViewModelContext.scopeInject(): Lazy<T> {
    return if (this is FragmentViewModelContext)
        this.fragment.inject()
    else
        this.activity.inject()
}
