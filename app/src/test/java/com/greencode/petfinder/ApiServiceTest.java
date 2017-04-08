package com.greencode.petfinder;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.entity.beans.pet.PetGetResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */
@Config(application = TestApplication.class)
public class ApiServiceTest  {

    private MockWebServer mockWebServer;

    @Mock
    ApiService apiService;

    private static final String PATH_GET_PET = "pet.get";
    private static final String PATG_FIND_PETS = "pet.find";
    SimpleXMLParser simpleXMLParser;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockWebServer = new MockWebServer();
        Serializer serializer = new Persister();
        simpleXMLParser = new SimpleXMLParser(serializer);
        mockWebServer.start();
        final Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                try {
                    switch (request.getPath()){
                        case PATH_GET_PET:
                                return new MockResponse()
                                        .setResponseCode(100)
                                        .setBody(simpleXMLParser.parse("xml/petasdfind.xml",PetGetResponse.class).toString().replaceAll("[^\\x20-\\x7e]", ""));
                        case PATG_FIND_PETS:
                            return new MockResponse()
                                    .setResponseCode(100)
                                    .setBody("");
                    }
                    return new MockResponse().setResponseCode(404);
                }catch (Exception ignored){}
                return new MockResponse();
            }
        };
        mockWebServer.setDispatcher(dispatcher);
    }

    @Test
    public void testPetGetSuccess() throws Exception {
        TestSubscriber<PetGetResponse> testSubscriber = new TestSubscriber<>();
        //apiService.getPet("test", "test").subscribe(testSubscriber);
        PetGetResponse getResponse = simpleXMLParser.parse("/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/petfind.xml",PetGetResponse.class);

    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
        mockWebServer.close();
    }

}
