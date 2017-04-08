package com.greencode.petfinder.data.cache;

import java.util.List;

import rx.Observable;

/**
 * Abstract cache
 *
 * @author Anton Kazakov
 * @date 31.03.17.
 */

public interface Cache<T> {

    void clearCache();

    Observable<List<T>> getAll();

    Observable<T> get(String id);

    void put(T t);

    void putAll(List<T> list);

    boolean isExpired();

}
