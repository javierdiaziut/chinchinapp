package cl.blackmind.core.coroutines

import cl.blackmind.core.extension.LiveResult
import cl.blackmind.core.extension.postCancel
import cl.blackmind.core.extension.postEmpty
import cl.blackmind.core.extension.postLoading
import cl.blackmind.core.extension.postSuccess
import cl.blackmind.core.extension.postThrowable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class ResultUnitUseCase<T>(
    override val backgroundContext: CoroutineContext,
    override val foregroundContext: CoroutineContext
) : BaseUnitUseCase<LiveResult<T>>(
    backgroundContext, foregroundContext
) {
    protected abstract suspend fun executeOnBackground(): T?

    override fun execute(liveData: LiveResult<T>) {
        CoroutineScope(foregroundContext + newJob()).launch {
            liveData.postLoading()

            runCatching {
                withContext(backgroundContext) { executeOnBackground()!! }
            }.onSuccess { response ->
                liveData.postSuccess(response)
            }.onFailure { throwable ->
                when (throwable) {
                    is CancellationException -> liveData.postCancel()
                    is NullPointerException -> liveData.postEmpty()
                    else -> liveData.postThrowable(throwable)
                }
            }
        }
    }
}
