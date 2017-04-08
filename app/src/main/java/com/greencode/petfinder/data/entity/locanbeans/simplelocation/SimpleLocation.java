package com.greencode.petfinder.data.entity.locanbeans.simplelocation;

import io.realm.RealmObject;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class SimpleLocation extends RealmObject{

    private double latitude;

    private double longitude;

    private long created;

    private String zipCode;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SimpleLocation{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", created=").append(created);
        sb.append('}');
        return sb.toString();
    }
}
