package com.greencode.petfinder.data.entity.locanbeans.simplelocation;

import io.realm.RealmObject;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SimpleLocation extends RealmObject{

    private String placeId;

    private String address;

    private long created;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
