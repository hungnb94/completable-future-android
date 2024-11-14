package com.leoh.concurrency;

import kotlin.coroutines.Continuation;

interface ContinuationCallback<T> {
    void invoke(Continuation<T> continuation);
}
