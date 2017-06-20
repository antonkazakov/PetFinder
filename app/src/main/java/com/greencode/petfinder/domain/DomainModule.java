package com.greencode.petfinder.domain;

import com.greencode.petfinder.domain.injection.JobThread;
import com.greencode.petfinder.domain.injection.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    @JobThread
    public Scheduler provideJobScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @UIThread
    public Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
