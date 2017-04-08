package com.greencode.petfinder.data.entity.locanbeans.pet;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class Contact extends RealmObject {

    private String phone;

    private String email;

    private String state;

    private String city;

    private String zipcode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
