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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!phone.equals(contact.phone)) return false;
        if (!email.equals(contact.email)) return false;
        if (!state.equals(contact.state)) return false;
        if (!city.equals(contact.city)) return false;
        return zipcode.equals(contact.zipcode);

    }

    @Override
    public int hashCode() {
        int result = phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zipcode.hashCode();
        return result;
    }
}
