package com.greencode.petfinder.data.entity.locanbeans.pet;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class Photo extends RealmObject {

    @PrimaryKey
    private String id;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
