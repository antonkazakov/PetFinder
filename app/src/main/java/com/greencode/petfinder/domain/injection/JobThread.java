package com.greencode.petfinder.domain.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author Anton Kazakov
 * @date 19.06.17.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface JobThread {
}
