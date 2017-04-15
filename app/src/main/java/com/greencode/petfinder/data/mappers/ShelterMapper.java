package com.greencode.petfinder.data.mappers;

import com.greencode.petfinder.data.entity.beans.shelter.ShelterFindResponse;
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


    public Shelter transform(ShelterFindResponse.Shelter shelterResponse) {
        Shelter shelter = new Shelter();
        shelter.setId(shelterResponse.getId());
        shelter.setName(shelterResponse.getName());
        shelter.setAddress(shelterResponse.getAddress1());
        shelter.setCity(shelterResponse.getCity());
        shelter.setState(shelterResponse.getState());
        shelter.setZip(shelterResponse.getZip());
        shelter.setCountry(shelterResponse.getCountry());
        shelter.setLatitude(Double.valueOf(shelterResponse.getLatitude()));
        shelter.setLongitude(Double.valueOf(shelterResponse.getLongitude()));
        shelter.setPhone(shelterResponse.getPhone());
        shelter.setFax(shelterResponse.getFax());
        shelter.setEmail(shelterResponse.getEmail());
        return shelter;
    }

    public List<Shelter> transform1(List<ShelterFindResponse.Shelter> shelterGetResponses) {
        List<Shelter> shelters = new RealmList<>();
        for (ShelterFindResponse.Shelter shelterGetResponse : shelterGetResponses){
            shelters.add(transform(shelterGetResponse));
        }
        return shelters;
    }


}
