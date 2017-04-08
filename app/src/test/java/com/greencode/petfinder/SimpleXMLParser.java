package com.greencode.petfinder;

import android.support.annotation.NonNull;

import org.simpleframework.xml.Serializer;

import java.io.File;

import javax.inject.Inject;

/**
 * @author Anton Kazakov
 * @date 30.03.17.
 */

public class SimpleXMLParser implements IParser {

    private Serializer serializer;

    @Inject
    public SimpleXMLParser(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public <T> T parse(@NonNull String source, @NonNull Class<T> clazz) throws Exception {
        return serializer.read(clazz, new File(source));
    }

}
