package com.greencode.petfinder.data.source;

import com.greencode.petfinder.SimpleXMLParser;
import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.simpleframework.xml.core.Persister;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;

/**
 * @author Anton Kazakov
 * @date 08.04.17.
 */
public class CloudPetDataSourceTest {

    @Mock
    ApiService apiService;

    private SimpleXMLParser simpleXMLParser;

    private static final String GET_PET_RESPONSE = "/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/petfind.xml";

    @Before
    public void setUp() throws Exception {
        simpleXMLParser = new SimpleXMLParser(new Persister());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindPet() throws Exception {
        Mockito.when(apiService.getPet(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Observable.just(simpleXMLParser.parse(GET_PET_RESPONSE, PetGetResponse.class)));
        TestSubscriber<PetGetResponse> testSubscriber = new TestSubscriber<>();
        apiService.getPet("dummy", "dummy").subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        assertEquals("37432659",testSubscriber.getOnNextEvents().get(0).getPet().getId());

    }

    @After
    public void tearDown() throws Exception {

    }



}