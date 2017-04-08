package com.greencode.petfinder.data.entity.mappers;

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
        pet.setDescription(petGetResponse.getPet().getDescription().getCdataSection());
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

    @Override
    public List<Pet> transform(List<PetGetResponse> petGetResponses) {
        List<Pet> pets = new ArrayList<>(20);
        for (PetGetResponse petGetResponse : petGetResponses){
            pets.add(transform(petGetResponse));
        }
        return pets;
    }
}
