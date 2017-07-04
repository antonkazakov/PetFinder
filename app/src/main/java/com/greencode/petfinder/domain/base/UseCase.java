package com.greencode.petfinder.domain.base;

import android.support.annotation.NonNull;

import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
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

    public void execute(ParameterType parameter, Observer<ResultType> observer) {
        compositeSubscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(observer));
    }

    /**
     * Execute observable with some actions
     * @param parameter
     * @param onSubscribeAction
     * @param onTerminateAction
     * @param observer
     */
    public void execute(ParameterType parameter,
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

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    public void clear() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

}
