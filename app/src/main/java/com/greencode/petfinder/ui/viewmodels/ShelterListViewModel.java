package com.greencode.petfinder.ui.viewmodels;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public class ShelterListViewModel {

    private String id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String phone;

    private String email;

    private String distanceFromPosition;

    double latitude;

    double longitude;

    /**
     * 0 - near < 10 km
     * 1 - middle < 50 km
     * 2 - far > 100 km
     */
    private Integer distanceFactor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public String getDistanceFromPosition() {
        return distanceFromPosition;
    }

    public void setDistanceFromPosition(String distanceFromPosition) {
        this.distanceFromPosition = distanceFromPosition;
    }

    public Integer getDistanceFactor() {
        return distanceFactor;
    }

    public void setDistanceFactor(Integer distanceFactor) {
        this.distanceFactor = distanceFactor;
    }

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
}
