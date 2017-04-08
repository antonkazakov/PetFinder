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

    public Photo() {
    }

    public Photo(String id, String url) {
        this.id = id;
        this.url = url;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (!id.equals(photo.id)) return false;
        return url.equals(photo.url);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Photo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
