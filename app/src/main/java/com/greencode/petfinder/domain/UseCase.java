package com.greencode.petfinder.domain;

import com.greencode.petfinder.domain.qualifiers.JobThread;
import com.greencode.petfinder.domain.qualifiers.UIThread;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
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

    protected abstract Observable<ResultType> buildObservable(ParameterType parameter);

    public void execute(ParameterType parameter, Subscriber<ResultType> subscriber) {
        compositeSubscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    public void clear() {
        if (compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

}
