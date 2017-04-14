package com.greencode.petfinder.data.entity.beans.pet;

import com.greencode.petfinder.data.entity.beans.BaseResponse;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.net.URL;
import java.util.List;

public class PetFindResponse extends BaseResponse {

    @ElementList(name="pets", required=false)
    List<Pet> pets;

    @Element(name="lastOffset", required=false)
    String lastOffset;

    @Attribute(name="noNamespaceSchemaLocation", required=false)
    URL noNamespaceSchemaLocation;

    public List<Pet> getPets() {return this.pets;}
    public void setPets(List<Pet> value) {this.pets = value;}

    public String getLastOffset() {return this.lastOffset;}
    public void setLastOffset(String value) {this.lastOffset = value;}

    public URL getNoNamespaceSchemaLocation() {return this.noNamespaceSchemaLocation;}
    public void setNoNamespaceSchemaLocation(URL value) {this.noNamespaceSchemaLocation = value;}

    public static class Description {

        @Element(name="#cdata-section", required=false)
        String cdataSection;

        public String getCdataSection() {return this.cdataSection;}
        public void setCdataSection(String value) {this.cdataSection = value;}

    }

    public static class Photo {

        @Text(required=false)
        String textValue;

        @Attribute(name="size", required=false)
        String size;

        @Attribute(name="id", required=false)
        Integer id;

        public String getTextValue() {return this.textValue;}
        public void setTextValue(String value) {this.textValue = value;}

        public String getSize() {return this.size;}
        public void setSize(String value) {this.size = value;}

        public Integer getId() {return this.id;}
        public void setId(Integer value) {this.id = value;}

    }

    public static class Media {

        @ElementList(name="photos", required=false)
        List<Photo> photos;

        public List<Photo> getPhotos() {return this.photos;}
        public void setPhotos(List<Photo> value) {this.photos = value;}

    }

    public static class Breeds {

        @Element(name="breed", required=false)
        String breed;

        public String getBreed() {return this.breed;}
        public void setBreed(String value) {this.breed = value;}

    }

    public static class Contact {

        @Element(name="zip", required=false)
        String zip;

        @Element(name="address2", required=false)
        String address2;

        @Element(name="city", required=false)
        String city;

        @Element(name="phone", required=false)
        String phone;

        @Element(name="address1", required=false)
        String address1;

        @Element(name="state", required=false)
        String state;

        @Element(name="fax", required=false)
        String fax;

        @Element(name="email", required=false)
        String email;

        public String getZip() {return this.zip;}
        public void setZip(String value) {this.zip = value;}

        public String getAddress2() {return this.address2;}
        public void setAddress2(String value) {this.address2 = value;}

        public String getCity() {return this.city;}
        public void setCity(String value) {this.city = value;}

        public String getPhone() {return this.phone;}
        public void setPhone(String value) {this.phone = value;}

        public String getAddress1() {return this.address1;}
        public void setAddress1(String value) {this.address1 = value;}

        public String getState() {return this.state;}
        public void setState(String value) {this.state = value;}

        public String getFax() {return this.fax;}
        public void setFax(String value) {this.fax = value;}

        public String getEmail() {return this.email;}
        public void setEmail(String value) {this.email = value;}

    }

    public static class Options {

        @Element(name="option", required=false)
        String option;

        public String getOption() {return this.option;}
        public void setOption(String value) {this.option = value;}

    }

    public static class Pet {

        @Element(name="sex", required=false)
        String sex;

        @Element(name="description", required=false)
        Description description;

        @Element(name="shelterId", required=false)
        String shelterId;

        @Element(name="media", required=false)
        Media media;

        @ElementList(name="breeds", required=false)
        List<Breeds> breeds;

        @Element(name="size", required=false)
        String size;

        @Element(name="lastUpdate", required=false)
        String lastUpdate;

        @Element(name="contact", required=false)
        Contact contact;

        @Element(name="name", required=false)
        String name;

        @ElementList(name="options", required=false)
        List<Options> options;

        @Element(name="animal", required=false)
        String animal;

        @Element(name="id", required=false)
        String id;

        @Element(name="mix", required=false)
        String mix;

        @Element(name="shelterPetId", required=false)
        String shelterPetId;

        @Element(name="age", required=false)
        String age;

        @Element(name="status", required=false)
        String status;

        public String getSex() {return this.sex;}
        public void setSex(String value) {this.sex = value;}

        public Description getDescription() {return this.description;}
        public void setDescription(Description value) {this.description = value;}

        public String getShelterId() {return this.shelterId;}
        public void setShelterId(String value) {this.shelterId = value;}

        public Media getMedia() {return this.media;}
        public void setMedia(Media value) {this.media = value;}

        public List<Breeds> getBreeds() {return this.breeds;}
        public void setBreeds(List<Breeds> value) {this.breeds = value;}

        public String getSize() {return this.size;}
        public void setSize(String value) {this.size = value;}

        public String getLastUpdate() {return this.lastUpdate;}
        public void setLastUpdate(String value) {this.lastUpdate = value;}

        public Contact getContact() {return this.contact;}
        public void setContact(Contact value) {this.contact = value;}

        public String getName() {return this.name;}
        public void setName(String value) {this.name = value;}

        public List<Options> getOptions() {return this.options;}
        public void setOptions(List<Options> value) {this.options = value;}

        public String getAnimal() {return this.animal;}
        public void setAnimal(String value) {this.animal = value;}

        public String getId() {return this.id;}
        public void setId(String value) {this.id = value;}

        public String getMix() {return this.mix;}
        public void setMix(String value) {this.mix = value;}

        public String getShelterPetId() {return this.shelterPetId;}
        public void setShelterPetId(String value) {this.shelterPetId = value;}

        public String getAge() {return this.age;}
        public void setAge(String value) {this.age = value;}

        public String getStatus() {return this.status;}
        public void setStatus(String value) {this.status = value;}

    }

}