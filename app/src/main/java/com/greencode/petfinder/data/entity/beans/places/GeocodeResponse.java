package com.greencode.petfinder.data.entity.beans.places;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

@Root(name="GeocodeResponse")
public class GeocodeResponse {

    @ElementList(name="result", required=false, entry="result", inline=true)
    List<Result> result;

    @Element(name="status", required=false)
    String status;

    public List<Result> getResult() {return this.result;}
    public void setResult(List<Result> value) {this.result = value;}

    public String getStatus() {return this.status;}
    public void setStatus(String value) {this.status = value;}

    public static class Result {

        @Element(name="formatted_address", required=false)
        String formattedAddress;

        @Element(name="geometry", required=false)
        Geometry geometry;

        @ElementList(name="type", required=false, entry="type", inline=true)
        List<String> type;

        @ElementList(name="address_component", required=false, entry="address_component", inline=true)
        List<AddressComponent> addressComponent;

        @Element(name="place_id", required=false)
        String placeId;

        public String getFormattedAddress() {return this.formattedAddress;}
        public void setFormattedAddress(String value) {this.formattedAddress = value;}

        public Geometry getGeometry() {return this.geometry;}
        public void setGeometry(Geometry value) {this.geometry = value;}

        public List<String> getType() {return this.type;}
        public void setType(List<String> value) {this.type = value;}

        public List<AddressComponent> getAddressComponent() {return this.addressComponent;}
        public void setAddressComponent(List<AddressComponent> value) {this.addressComponent = value;}

        public String getPlaceId() {return this.placeId;}
        public void setPlaceId(String value) {this.placeId = value;}

    }

    public static class Southwest {

        @Element(name="lng", required=false)
        String lng;

        @Element(name="lat", required=false)
        String lat;

        public String getLng() {return this.lng;}
        public void setLng(String value) {this.lng = value;}

        public String getLat() {return this.lat;}
        public void setLat(String value) {this.lat = value;}

    }

    public static class Viewport {

        @Element(name="southwest", required=false)
        Southwest southwest;

        @Element(name="northeast", required=false)
        Northeast northeast;

        public Southwest getSouthwest() {return this.southwest;}
        public void setSouthwest(Southwest value) {this.southwest = value;}

        public Northeast getNortheast() {return this.northeast;}
        public void setNortheast(Northeast value) {this.northeast = value;}

    }

    public static class Bounds {

        @Element(name="southwest", required=false)
        Southwest southwest;

        @Element(name="northeast", required=false)
        Northeast northeast;

        public Southwest getSouthwest() {return this.southwest;}
        public void setSouthwest(Southwest value) {this.southwest = value;}

        public Northeast getNortheast() {return this.northeast;}
        public void setNortheast(Northeast value) {this.northeast = value;}

    }

    public static class Northeast {

        @Element(name="lng", required=false)
        String lng;

        @Element(name="lat", required=false)
        String lat;

        public String getLng() {return this.lng;}
        public void setLng(String value) {this.lng = value;}

        public String getLat() {return this.lat;}
        public void setLat(String value) {this.lat = value;}

    }

    public static class Geometry {

        @Element(name="viewport", required=false)
        Viewport viewport;

        @Element(name="bounds", required=false)
        Bounds bounds;

        @Element(name="location", required=false)
        Location location;

        @Element(name="location_type", required=false)
        String locationType;

        public Viewport getViewport() {return this.viewport;}
        public void setViewport(Viewport value) {this.viewport = value;}

        public Bounds getBounds() {return this.bounds;}
        public void setBounds(Bounds value) {this.bounds = value;}

        public Location getLocation() {return this.location;}
        public void setLocation(Location value) {this.location = value;}

        public String getLocationType() {return this.locationType;}
        public void setLocationType(String value) {this.locationType = value;}

    }

    public static class Location {

        @Element(name="lng", required=false)
        String lng;

        @Element(name="lat", required=false)
        String lat;

        public String getLng() {return this.lng;}
        public void setLng(String value) {this.lng = value;}

        public String getLat() {return this.lat;}
        public void setLat(String value) {this.lat = value;}

    }

    public static class AddressComponent {

        @Element(name="short_name", required=false)
        String shortName;

        @ElementList(name="type", required=false, entry="type", inline=true)
        List<String> type;

        @Element(name="long_name", required=false)
        String longName;

        public String getShortName() {return this.shortName;}
        public void setShortName(String value) {this.shortName = value;}

        public List<String> getType() {return this.type;}
        public void setType(List<String> value) {this.type = value;}

        public String getLongName() {return this.longName;}
        public void setLongName(String value) {this.longName = value;}

    }

}