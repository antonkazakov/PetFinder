package com.greencode.petfinder.utils;

import android.support.annotation.NonNull;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */

public interface IParser {

    <T> T parse(@NonNull String source, @NonNull Class<T> clazz) throws Exception;

}
