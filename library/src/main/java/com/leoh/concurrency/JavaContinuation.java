package com.leoh.concurrency;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

class JavaContinuation<T> implements Continuation<T> {
    private final CompletableFuture<T> future;
    private final CoroutineContext coroutineContext;

    public JavaContinuation(CompletableFuture<T> future, CoroutineContext coroutineContext) {
        this.future = future;
        this.coroutineContext = coroutineContext;
    }

    public JavaContinuation(CompletableFuture<T> future) {
        this.future = future;
        this.coroutineContext = EmptyCoroutineContext.INSTANCE;
    }

    /** @noinspection unchecked*/
    @Override
    public void resumeWith(@NotNull Object o) {
        if (o instanceof Result.Failure)
            future.completeExceptionally(((Result.Failure) o).exception);
        else
            future.complete((T) o);
    }

    @NotNull
    @Override
    public CoroutineContext getContext() {
        return coroutineContext;
    }
}
