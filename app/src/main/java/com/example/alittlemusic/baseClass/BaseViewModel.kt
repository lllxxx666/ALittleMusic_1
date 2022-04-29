package com.example.alittlemusic.baseClass

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit


open class BaseViewModel : AndroidViewModel(MyApplication()){
    /*
   * 启动协程
   */
    protected fun launch(block: Block<Unit>, error: Error? = null, cancel: Cancel? = null): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        handleError(e)
                        error?.invoke(e)
                    }
                }
            }
        }
    }

    /*
    * 启动协程
    */
    protected fun <T> async(block: Block<T>): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }

    /**
     * 处理异常
     */
    private fun handleError(e: Exception) {
        Toast.makeText(MyApplication.context, e.message, Toast.LENGTH_SHORT).show()
    }

}