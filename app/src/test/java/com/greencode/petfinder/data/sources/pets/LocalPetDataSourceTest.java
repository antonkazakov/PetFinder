package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
 * @date 17.05.17.
 */
public class LocalPetDataSourceTest {

    @Mock
    PetCache petCache;

    private LocalPetDataSource localPetDataSource;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        localPetDataSource = new LocalPetDataSource(petCache);
    }

    @Test
    public void getPet() throws Exception {
        Pet pet = new Pet();
        //given
        when(petCache.get(anyString())).thenReturn(Observable.just(pet));

        TestSubscriber<Pet> petTestSubscriber = new TestSubscriber<>();
        localPetDataSource.getPet("dummy").subscribe(petTestSubscriber);

        //then
        petTestSubscriber.assertCompleted();
        petTestSubscriber.assertNoErrors();
        assertEquals(pet, petTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void findPet() throws Exception {
        List<Pet> pets = fakePets();
        //given
        when(petCache.getAll()).thenReturn(Observable.just(pets));

        TestSubscriber<List<Pet>> petTestSubscriber = new TestSubscriber<>();
        localPetDataSource.findPet(new HashMap<>()).subscribe(petTestSubscriber);

        //then
        petTestSubscriber.assertNoErrors();
        petTestSubscriber.assertCompleted();
        assertEquals(pets.get(0),petTestSubscriber.getOnNextEvents().get(0).get(0));
    }

    @Test
    @Ignore
    public void getRandomPet() throws Exception {

    }

    @Test
    public void getSheltersPet() throws Exception {
        List<Pet> pets = fakePets();
        //given
        when(petCache.getAll()).thenReturn(Observable.just(pets));

        TestSubscriber<List<Pet>> petTestSubscriber = new TestSubscriber<>();
        localPetDataSource.getSheltersPet(new HashMap<>()).subscribe(petTestSubscriber);

        //then
        petTestSubscriber.assertNoErrors();
        petTestSubscriber.assertCompleted();
        assertEquals(pets.get(0),petTestSubscriber.getOnNextEvents().get(0).get(0));
    }

    public List<Pet> fakePets(){
        return new ArrayList<Pet>(){{
            add(new Pet());
            add(new Pet());
            add(new Pet());
            add(new Pet());
        }};
    }

}