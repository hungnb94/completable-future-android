package com.leoh.concurrency;

import java.util.concurrent.CompletableFuture;

import kotlin.coroutines.CoroutineContext;

public class SuspendFunctionUtils<T> {
    public <T> CompletableFuture<T> getCompletableFuture(ContinuationCallback<T> listener) {
        CompletableFuture<T> suspendResult = new CompletableFuture<>();
        listener.invoke(new JavaContinuation<>(suspendResult));
        return suspendResult;
    }

    public <T> CompletableFuture<T> getCompletableFuture(
            ContinuationCallback<T> listener,
            CoroutineContext coroutineContext
    ) {
        CompletableFuture<T> suspendResult = new CompletableFuture<>();
        listener.invoke(new JavaContinuation<>(suspendResult, coroutineContext));
        return suspendResult;
    }
}
