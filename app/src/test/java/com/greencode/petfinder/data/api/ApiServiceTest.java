package com.greencode.petfinder.data.api;

import com.greencode.petfinder.SimpleXMLParser;
import com.greencode.petfinder.data.entity.beans.pet.PetFindResponse;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;
import com.greencode.petfinder.data.entity.beans.shelter.ShelterFindResponse;
import com.greencode.petfinder.data.entity.beans.shelter.ShelterGetResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

/**
 * @author Anton Kazakov
 * @date 17.05.17.
 */
public class ApiServiceTest {

    @Inject
    ApiService apiService;

    private MockWebServer mockWebServer;
    private Persister persister;
    private SimpleXMLParser simpleXMLParser;

    private static final String PATH_GET_PET = "pet.get";
    private static final String RESPONSE_GET_PET = "";

    private static final String PATH_GET_PET_RANDOM = "pet.getRandom";
    private static final String RESPONSE_GET_PET_RANDOM = "";

    @Before
    public void setUp() throws Exception {

        //TestComponent testComponent = Dagger
       // Dagger
        persister = new Persister();
        simpleXMLParser = new SimpleXMLParser(persister);

        mockWebServer = new MockWebServer();
        mockWebServer.start();
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                switch (request.getPath()) {
                    case PATH_GET_PET:
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(simpleXMLParser.parse(RESPONSE_GET_PET, PetGetResponse.class).toString());
                    case PATH_GET_PET_RANDOM:
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(simpleXMLParser.parse(RESPONSE_GET_PET_RANDOM, PetGetResponse.class).toString());
                    default:
                        return new MockResponse().setResponseCode(404);
                }
            }
        };
        mockWebServer.setDispatcher(dispatcher);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void getPet() throws Exception {
        TestSubscriber<PetGetResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.getPet("", "").subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();

    }

    @Test
    public void getRandomPet() throws Exception {
        TestSubscriber<PetGetResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.getRandomPet(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Test
    public void findPet() throws Exception {
        TestSubscriber<PetFindResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.findPet(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Test
    public void getSheltersPet() throws Exception {
        TestSubscriber<PetFindResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.getSheltersPet(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Test
    public void findShelter() throws Exception {
        TestSubscriber<ShelterFindResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.findShelter(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Test
    public void getShelter() throws Exception {
        TestSubscriber<ShelterGetResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.getShelter(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Test
    public void breedsInShelter() throws Exception {
        TestSubscriber<ShelterFindResponse> petGetResponseTestSubscriber = new TestSubscriber<>();
        apiService.breedsInShelter(new HashMap<>()).subscribe(petGetResponseTestSubscriber);

        petGetResponseTestSubscriber.assertNoErrors();
        petGetResponseTestSubscriber.assertCompleted();
    }

    @Ignore
    @Test
    public void convertToAddress() throws Exception {
    }



}