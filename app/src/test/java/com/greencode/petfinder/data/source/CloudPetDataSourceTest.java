package com.greencode.petfinder.data.source;

import com.greencode.petfinder.SimpleXMLParser;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.mappers.PetMapper;
import com.greencode.petfinder.data.source.pet.CloudPetDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.simpleframework.xml.core.Persister;

import dagger.Lazy;
import io.realm.Realm;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

/**
 * @author Anton Kazakov
 * @date 08.04.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Realm.class)
public class CloudPetDataSourceTest{

    @Mock
    ApiService apiService;

    @Mock
    PetMapper petMapper;

    PetCache petCache;

    private Realm realm;
    private CloudPetDataSource cloudPetDataSource;

    private SimpleXMLParser simpleXMLParser;

    private static final String GET_PET_RESPONSE = "/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/petfind.xml";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // petMapper = new PetMapper();
        realm = PowerMockito.mock(Realm.class);
        Lazy<Realm> realmLazy = () -> realm;
        petCache = new PetCache();
        simpleXMLParser = new SimpleXMLParser(new Persister());
        cloudPetDataSource = new CloudPetDataSource(apiService, petMapper, petCache);
    }

    @Test
    public void testFindPet() throws Exception {
        Mockito.when(apiService.getPet(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class)));
        TestSubscriber<PetGetResponse> testSubscriber = new TestSubscriber<>();
        apiService.getPet("dummy", "dummy").subscribe(testSubscriber);

        Mockito.verify(apiService).getPet("dummy", "dummy");

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        assertEquals("37432659", testSubscriber.getOnNextEvents().get(0).getPet().getId());
    }

    @Test
    public void test() throws Exception {
        Mockito.when(apiService.getPet(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class)));
        Mockito.when(petMapper.transform(Mockito.any(PetGetResponse.class))).thenCallRealMethod();
        TestSubscriber<Pet> testSubscriber = new TestSubscriber<>();
        cloudPetDataSource.getPet("dummy").subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        //Pet pet = testSubscriber.getOnNextEvents().get(0);
        //assertEquals("37432659", pet.getId());
    }

    @After
    public void tearDown() throws Exception {

    }



}