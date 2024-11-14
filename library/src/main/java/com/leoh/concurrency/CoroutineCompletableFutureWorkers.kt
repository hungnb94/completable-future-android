package com.leoh.concurrency

import kotlinx.coroutines.Dispatchers

object CoroutineCompletableFutureWorkers {
    @JvmStatic
    val Computation by lazy { CoroutineCompletableFutureWorker(Dispatchers.Default) }

    @JvmStatic
    val Main by lazy { CoroutineCompletableFutureWorker(Dispatchers.Main) }

    @JvmStatic
    val IO by lazy { CoroutineCompletableFutureWorker(Dispatchers.IO) }
}
