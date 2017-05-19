package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.sources.pets.CloudPetDataSource;
import com.greencode.petfinder.data.sources.pets.LocalPetDataSource;
import com.greencode.petfinder.data.sources.pets.PetDataSource;
import com.greencode.petfinder.data.sources.pets.PetSourcePetFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 * @author Anton Kazakov
 * @date 19.05.17.
 */
public class PetRepositoryTest {

    @Mock
    PetSourcePetFactory petSourcePetFactory;

    @Mock
    PetDataSource petDataSource;

    @Mock
    CloudPetDataSource cloudPetDataSource;

    @Mock
    PetCache petCache;

    private PetRepository petRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        petRepository = new PetRepository(petSourcePetFactory);

        when(petSourcePetFactory.createCloudDataSource()).thenReturn(cloudPetDataSource);
        when(petSourcePetFactory.createDependingOnCache()).thenReturn(petDataSource);
    }

    @Test
    public void getPetDependingOnCache() throws Exception {
        Pet pet = new Pet();

        when(petSourcePetFactory.createDependingOnCache().getPet(anyString())).thenReturn(Observable.just(pet));

        TestSubscriber<Pet> petTestSubscriber = new TestSubscriber<>();
        petRepository.getPet("dummy").subscribe(petTestSubscriber);
        petTestSubscriber.awaitTerminalEvent();

        petTestSubscriber.assertCompleted();
        petTestSubscriber.assertNoErrors();
        assertEquals(pet, petTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void findPet() throws Exception {
        List<Pet> pets = fakePets();

        when(petSourcePetFactory.createCloudDataSource().findPet(anyMap())).thenReturn(Observable.just(pets));

        TestSubscriber<List<Pet>> petTestSubscriber = new TestSubscriber<>();
        petRepository.findPet(new HashMap<>()).subscribe(petTestSubscriber);
        petTestSubscriber.awaitTerminalEvent();

        petTestSubscriber.assertCompleted();
        petTestSubscriber.assertNoErrors();
        verify(petSourcePetFactory.createCloudDataSource(),times(1));
        assertEquals(pets, petTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void getRandomPet() throws Exception {
        Pet pet = new Pet();

        when(petSourcePetFactory.createCloudDataSource().getRandomPet(anyMap())).thenReturn(Observable.just(pet));

        TestSubscriber<Pet> petTestSubscriber = new TestSubscriber<>();
        petRepository.getRandomPet(new HashMap<>()).subscribe(petTestSubscriber);
        petTestSubscriber.awaitTerminalEvent();

        petTestSubscriber.assertCompleted();
        petTestSubscriber.assertNoErrors();
        verify(petSourcePetFactory.createCloudDataSource(),times(1));
        assertEquals(pet, petTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    /**
     * This test will fail for now because need to check is pets from this shelter are in base and check its
     * count if it too small then fuck cache and go to network.
     */
    // TODO: 19.05.17 refactor
    public void getSheltersPet() throws Exception {
        List<Pet> pets = fakePets();

        when(petSourcePetFactory.createDependingOnCache().getSheltersPet(anyMap())).thenReturn(Observable.just(pets));

        TestSubscriber<List<Pet>> petTestSubscriber = new TestSubscriber<>();
        petRepository.findPet(new HashMap<>()).subscribe(petTestSubscriber);
        petTestSubscriber.awaitTerminalEvent();

        petTestSubscriber.assertCompleted();
        petTestSubscriber.assertNoErrors();
        verify(petSourcePetFactory.createCloudDataSource(),times(1));
        assertEquals(pets, petTestSubscriber.getOnNextEvents().get(0));
    }

    /**
     * Fake pet list
     * @return
     */
    private List<Pet> fakePets(){
        return new ArrayList<Pet>(){{
            add(new Pet());
        }};
    }

}