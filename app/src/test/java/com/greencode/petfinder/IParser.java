package com.greencode.petfinder;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */

public interface IParser {

    <T> T parse(@NonNull String source, @NonNull Class<T> clazz) throws Exception;

}
