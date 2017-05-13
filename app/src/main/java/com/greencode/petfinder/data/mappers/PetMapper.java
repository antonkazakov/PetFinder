package com.greencode.petfinder.data.mappers;

import com.greencode.petfinder.data.entity.beans.pet.PetFindResponse;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Contact;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.pet.Photo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class PetMapper implements Mapper<PetGetResponse,Pet> {

    private static final String BIG_PHOTO = "x";

    @Inject
    public PetMapper() {
    }

    @Override
    public Pet transform(PetGetResponse petGetResponse) {
        Pet pet = new Pet();
        pet.setId(petGetResponse.getPet().getId());
        pet.setName(petGetResponse.getPet().getName());
        pet.setSex(petGetResponse.getPet().getSex());
        pet.setDescription(petGetResponse.getPet().getDescription());
        pet.setAnimal(petGetResponse.getPet().getAnimal());
        pet.setAge(petGetResponse.getPet().getAge());
        pet.setSize(petGetResponse.getPet().getSize());

        RealmList<Photo> photoRealmList = new RealmList<>();
        for (PetGetResponse.Photo photoItem : petGetResponse.getPet().getMedia().getPhotos()){
            if (photoItem.getSize().equals(BIG_PHOTO)){
                Photo photo = new Photo();
                photo.setId(petGetResponse.getPet().getId()+ "_" + photoItem.getId().toString());
                photo.setUrl(photoItem.getTextValue());
                photoRealmList.add(photo);
            }
        }
        pet.setPhotos(photoRealmList);

        Contact contact = new Contact();
        contact.setCity(petGetResponse.getPet().getContact().getCity());
        contact.setEmail(petGetResponse.getPet().getContact().getEmail());
        contact.setPhone(petGetResponse.getPet().getContact().getPhone());
        contact.setState(petGetResponse.getPet().getContact().getState());
        contact.setZipcode(petGetResponse.getPet().getContact().getZip());
        pet.setContact(contact);
        return pet;
    }

    public Pet transform(PetFindResponse.Pet petResponse) {
        Pet pet = new Pet();
        pet.setId(petResponse.getId());
        pet.setName(petResponse.getName());
        pet.setSex(petResponse.getSex());
        pet.setDescription(petResponse.getDescription().getCdataSection());
        pet.setAnimal(petResponse.getAnimal());
        pet.setAge(petResponse.getAge());
        pet.setSize(petResponse.getSize());

        RealmList<Photo> photoRealmList = new RealmList<>();
        if (petResponse.getMedia().getPhotos() != null) {
            for (PetFindResponse.Photo photoItem : petResponse.getMedia().getPhotos()) {
                if (photoItem.getSize().equals(BIG_PHOTO)) {
                    Photo photo = new Photo();
                    photo.setId(petResponse.getId() + "_" + photoItem.getId().toString());
                    photo.setUrl(photoItem.getTextValue());
                    photoRealmList.add(photo);
                }
            }
            pet.setPhotos(photoRealmList);
        }
        Contact contact = new Contact();
        contact.setCity(petResponse.getContact().getCity());
        contact.setEmail(petResponse.getContact().getEmail());
        contact.setPhone(petResponse.getContact().getPhone());
        contact.setState(petResponse.getContact().getState());
        contact.setZipcode(petResponse.getContact().getZip());
        pet.setContact(contact);
        return pet;
    }

    @Override
    public List<Pet> transform(List<PetGetResponse> petGetResponses) {
        List<Pet> pets = new ArrayList<>();
        for (PetGetResponse petGetResponse : petGetResponses){
            pets.add(transform(petGetResponse));
        }
        return pets;
    }

    public List<Pet> transform1(List<PetFindResponse.Pet> petGetResponses) {
        List<Pet> pets = new ArrayList<>();
        for (PetFindResponse.Pet petGetResponse : petGetResponses){
            pets.add(transform(petGetResponse));
        }
        return pets;
    }


}
