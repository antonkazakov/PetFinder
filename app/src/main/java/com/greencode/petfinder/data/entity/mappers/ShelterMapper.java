package com.greencode.petfinder.data.entity.mappers;

import com.greencode.petfinder.data.entity.beans.shelter.ShelterGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class ShelterMapper implements Mapper<ShelterGetResponse, Shelter> {

    @Inject
    public ShelterMapper() {
    }

    @Override
    public Shelter transform(ShelterGetResponse shelterGetResponse) {
        Shelter shelter = new Shelter();
        shelter.setId(shelterGetResponse.getShelter().getId());
        shelter.setName(shelterGetResponse.getShelter().getName());
        shelter.setAddress(shelterGetResponse.getShelter().getAddress1());
        shelter.setCity(shelterGetResponse.getShelter().getCity());
        shelter.setState(shelterGetResponse.getShelter().getState());
        shelter.setZip(shelterGetResponse.getShelter().getZip());
        shelter.setCountry(shelterGetResponse.getShelter().getCountry());
        shelter.setLatitude(Double.valueOf(shelterGetResponse.getShelter().getLatitude()));
        shelter.setLongitude(Double.valueOf(shelterGetResponse.getShelter().getLongitude()));
        shelter.setPhone(shelterGetResponse.getShelter().getPhone());
        shelter.setFax(shelterGetResponse.getShelter().getFax());
        shelter.setEmail(shelterGetResponse.getShelter().getEmail());
        return shelter;
    }

    @Override
    public List<Shelter> transform(List<ShelterGetResponse> shelterGetResponses) {
        List<Shelter> shelters = new RealmList<>();
        for (ShelterGetResponse shelterGetResponse : shelterGetResponses){
            shelters.add(transform(shelterGetResponse));
        }
        return shelters;
    }

}
