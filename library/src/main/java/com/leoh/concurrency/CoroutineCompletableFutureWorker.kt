package com.leoh.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.CoroutineContext

class CoroutineCompletableFutureWorker(
    dispatcher: CoroutineContext,
) {
    private val supervisorJob = SupervisorJob()
    private val coroutineScope = CoroutineScope(dispatcher + supervisorJob)

    fun run(block: suspend () -> Unit): CompletableFuture<Unit> =
        coroutineScope.future {
            block()
        }

    fun <T> execute(block: suspend () -> T?): CompletableFuture<T?> =
        coroutineScope.future {
            block()
        }

    fun cancel() {
        coroutineScope.cancel()
    }

    fun cancelChildren() {
        supervisorJob.cancelChildren()
    }
}
