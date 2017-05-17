package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.SimpleXMLParser;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.beans.pet.PetFindResponse;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.sources.pets.CloudPetDataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.simpleframework.xml.core.Persister;

import java.util.HashMap;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * @author Anton Kazakov
 * @date 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CloudPetDataSourceTest {

    private static final String GET_PET_RESPONSE = "/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/petfind.xml";

    private Persister persister;
    private SimpleXMLParser simpleXMLParser;
    private CloudPetDataSource cloudPetDataSource;

    @Mock
    ApiService apiService;

    @Mock
    PetMapper petMapper;

    @Mock
    PetCache petCache;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        persister = new Persister();
        simpleXMLParser = new SimpleXMLParser(persister);
        cloudPetDataSource = new CloudPetDataSource(apiService, petMapper, petCache);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getPetSuccessTest() throws Exception {
        Mockito.when(apiService.getPet(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class)));

        TestSubscriber<PetGetResponse> testSubscriber = new TestSubscriber<>();

        apiService.getPet("", "").subscribe(testSubscriber);
        // Mockito.verify(petMapper,Mockito.times(1)).transform(Mockito.any(PetGetResponse.class));
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        Assert.assertEquals("37432659", testSubscriber.getOnNextEvents().get(0).getPet().getId());
    }

    @Test
    public void findPetSuccessTest() throws Exception {
        Mockito.when(apiService.findPet(Mockito.anyMap()))
                .thenReturn(Observable.just(simpleXMLParser.parse("", PetFindResponse.class)));

        TestSubscriber<PetFindResponse> testSubscriber = new TestSubscriber<>();

        apiService.findPet(new HashMap<>()).subscribe(testSubscriber);

        //Mockito.verify(petCache, Mockito.times(1)).putAll(Mockito.anyList());
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        //Assert.assertEquals("", testSubscriber.getOnNextEvents().get(0).getPets().get(0).getId());
    }


    @Test
    public void getRandomPet() throws Exception {
        Mockito.when(apiService.getRandomPet(Mockito.anyMap()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class)));

        TestSubscriber<PetGetResponse> testSubscriber = new TestSubscriber<>();

        apiService.getRandomPet(new HashMap<>()).subscribe(testSubscriber);
        //Mockito.verify(petMapper,Mockito.times(1)).transform(Mockito.any(PetGetResponse.class));
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        Assert.assertEquals("37432659", testSubscriber.getOnNextEvents().get(0).getPet().getId());
    }

    @Test
    public void getSheltersPet() throws Exception {
        Mockito.when(apiService.getSheltersPet(Mockito.anyMap()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetFindResponse.class)));

        TestSubscriber<PetFindResponse> testSubscriber = new TestSubscriber<>();

        apiService.getSheltersPet(new HashMap<>()).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
    }

}