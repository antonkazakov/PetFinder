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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleLocation that = (SimpleLocation) o;

        if (created != that.created) return false;
        if (placeId != null ? !placeId.equals(that.placeId) : that.placeId != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (int) (created ^ (created >>> 32));
        return result;
    }
}
