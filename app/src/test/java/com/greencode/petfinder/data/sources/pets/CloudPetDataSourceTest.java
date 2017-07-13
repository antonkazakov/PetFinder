package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.beans.pet.PetFindResponse;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.utils.SimpleXMLParser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.simpleframework.xml.core.Persister;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Anton Kazakov
 * @date 17.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CloudPetDataSourceTest {

    private static final String GET_PET_RESPONSE = "xml/get_pet.xml";

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

        when(apiService.getPet(anyString()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE,
                        PetGetResponse.class)));


        when(petMapper.transform(any(PetGetResponse.class)))
                .thenCallRealMethod();
        TestSubscriber<Pet> testSubscriber = new TestSubscriber<>();
        cloudPetDataSource.getPet("dummy").subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        verify(petMapper, times(1)).transform(any(PetGetResponse.class));
        testSubscriber.assertNoErrors();
        Assert.assertEquals("37432659", testSubscriber.getOnNextEvents().get(0).getId());
    }

    @Test
    public void findPetSuccessTest() throws Exception {
        when(apiService.findPet(anyMap()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetFindResponse.class)));
        when(petMapper.transform(any(PetGetResponse.class))).thenCallRealMethod();
        TestSubscriber<List<Pet>> testSubscriber = new TestSubscriber<>();

        cloudPetDataSource.findPet(new HashMap<>()).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        Assert.assertEquals("37432659", testSubscriber.getOnNextEvents().get(0).get(0).getId());
    }


    @Test
    public void getRandomPet() throws Exception {
        when(apiService.getRandomPet(anyMap()))
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
        when(apiService.getSheltersPet(anyMap()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetFindResponse.class)));

        TestSubscriber<PetFindResponse> testSubscriber = new TestSubscriber<>();

        apiService.getSheltersPet(new HashMap<>()).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
    }

}