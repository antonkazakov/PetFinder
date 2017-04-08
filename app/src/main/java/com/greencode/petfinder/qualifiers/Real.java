package com.greencode.petfinder.qualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author Anton Kazakov
 * @date 07.04.17.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Real {
}
