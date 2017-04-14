package com.greencode.petfinder.data.entity.beans.shelter;

import com.greencode.petfinder.data.entity.beans.BaseResponse;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

@Root(name="petfinder")
public class ShelterFindResponse extends BaseResponse {

    @Element(name="lastOffset", required=false)
    String lastOffset;

    @ElementList(name="shelters", required=false)
    List<Shelter> shelters;

    public String getLastOffset() {return this.lastOffset;}
    public void setLastOffset(String value) {this.lastOffset = value;}

    public List<Shelter> getShelters() {return this.shelters;}
    public void setShelters(List<Shelter> value) {this.shelters = value;}

    public static class Shelter {

        @Element(name="zip", required=false)
        String zip;

        @Element(name="country", required=false)
        String country;

        @Element(name="address2", required=false)
        String address2;

        @Element(name="city", required=false)
        String city;

        @Element(name="address1", required=false)
        String address1;

        @Element(name="latitude", required=false)
        String latitude;

        @Element(name="phone", required=false)
        String phone;

        @Element(name="name", required=false)
        String name;

        @Element(name="id", required=false)
        String id;

        @Element(name="state", required=false)
        String state;

        @Element(name="fax", required=false)
        String fax;

        @Element(name="email", required=false)
        String email;

        @Element(name="longitude", required=false)
        String longitude;

        public String getZip() {return this.zip;}
        public void setZip(String value) {this.zip = value;}

        public String getCountry() {return this.country;}
        public void setCountry(String value) {this.country = value;}

        public String getAddress2() {return this.address2;}
        public void setAddress2(String value) {this.address2 = value;}

        public String getCity() {return this.city;}
        public void setCity(String value) {this.city = value;}

        public String getAddress1() {return this.address1;}
        public void setAddress1(String value) {this.address1 = value;}

        public String getLatitude() {return this.latitude;}
        public void setLatitude(String value) {this.latitude = value;}

        public String getPhone() {return this.phone;}
        public void setPhone(String value) {this.phone = value;}

        public String getName() {return this.name;}
        public void setName(String value) {this.name = value;}

        public String getId() {return this.id;}
        public void setId(String value) {this.id = value;}

        public String getState() {return this.state;}
        public void setState(String value) {this.state = value;}

        public String getFax() {return this.fax;}
        public void setFax(String value) {this.fax = value;}

        public String getEmail() {return this.email;}
        public void setEmail(String value) {this.email = value;}

        public String getLongitude() {return this.longitude;}
        public void setLongitude(String value) {this.longitude = value;}

    }

}