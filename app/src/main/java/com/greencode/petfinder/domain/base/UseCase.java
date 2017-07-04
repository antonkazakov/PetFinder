package com.greencode.petfinder.domain.base;

import android.support.annotation.NonNull;

import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Anton Kazakov
 * @date 19.06.17.
 */

public abstract class UseCase<ResultType, ParameterType> {

    private Scheduler uiScheduler;
    private Scheduler jobScheduler;
    private CompositeSubscription compositeSubscription;

    public UseCase(@UIThread Scheduler uiScheduler, @JobThread Scheduler jobScheduler) {
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
        compositeSubscription = new CompositeSubscription();
    }

    @NonNull
    protected abstract Observable<ResultType> buildObservable(ParameterType parameter);

    // TODO: 04.07.17 possible improvements.

    /**
     * Build observable but do not execute it. Now I'm using it when need continuing down the chain.
     * But I bet it' not the best solution here.
     *
     * @param parameter
     * @return
     */
    @NonNull
    public Observable<ResultType> buildAndNoExecute(ParameterType parameter) {
        return buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler);
    }

    /**
     * Build and execute observable
     *
     * @param parameter
     * @param observer
     */
    public void buildAndExecute(ParameterType parameter, Observer<ResultType> observer) {
        compositeSubscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(observer));
    }

    /**
     * Execute observable with some actions
     *
     * @param parameter
     * @param onSubscribeAction
     * @param onTerminateAction
     * @param observer
     */
    public void buildAndExecute(ParameterType parameter,
                                Action0 onSubscribeAction,
                                Action0 onTerminateAction,
                                Observer<ResultType> observer) {
        compositeSubscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .doOnSubscribe(onSubscribeAction)
                .doOnTerminate(onTerminateAction)
                .subscribe(observer));
    }

    /**
     * Build and execute observable without parameters
     *
     * @param observer
     */
    public void buildAndExecute(Observer<ResultType> observer) {
        buildAndExecute(null, observer);
    }

    public void clear() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

}
