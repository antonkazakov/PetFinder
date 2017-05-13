package com.greencode.petfinder.data.entity.locanbeans.pet;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class Pet extends RealmObject{

    @PrimaryKey
    private String id;

    private String name;

    private String sex;

    private String description;

    private String animal;

    private String age;

    private String size;

    private RealmList<Photo> photos;

    private Contact contact;

    @Index
    private String TABLE;

    public Pet() {
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public RealmList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(RealmList<Photo> photos) {
        this.photos = photos;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public String getTABLE() {
        return TABLE;
    }

    public void setTABLE(String TABLE) {
        this.TABLE = TABLE;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Pet{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", animal='").append(animal).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", size='").append(size).append('\'');
        sb.append(", photos=").append(photos);
        sb.append(", contact=").append(contact);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (!id.equals(pet.id)) return false;
        if (!name.equals(pet.name)) return false;
        if (!sex.equals(pet.sex)) return false;
        if (!animal.equals(pet.animal)) return false;
        if (!age.equals(pet.age)) return false;
        if (!size.equals(pet.size)) return false;
        return contact.equals(pet.contact);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + animal.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + contact.hashCode();
        return result;
    }
}
