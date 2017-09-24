/*
 * Copyright 2017 Amit Kumar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.amitkma.boilerplate.domain.interactor;

import com.github.amitkma.boilerplate.domain.executor.PostExecutionThread;
import com.github.amitkma.boilerplate.domain.executor.ThreadExecutor;
import com.google.common.base.Preconditions;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Author: Amit Kumar
 * Created on: 19/9/17.
 *
 * Abstract class for a Use Case.
 * This interface represents a super use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link
 * DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */

public abstract class UseCase<T, P> {

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private final CompositeDisposable mDisposables;

    protected UseCase(ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
        mDisposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    abstract Flowable<T> buildUseCaseObservable(P params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(P)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableSubscriber<T> observer, P params) {
        Preconditions.checkNotNull(observer);
        final Flowable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(mDisposables);
        mDisposables.add(disposable);
    }

}
