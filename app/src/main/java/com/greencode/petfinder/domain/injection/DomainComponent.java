package com.greencode.petfinder.domain.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Anton Kazakov
 * @date 20.06.17.
 */
@Component(modules = DomainModule.class)
@Singleton
public interface DomainComponent {



}
