package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.simplelocation.SimpleLocation;
import com.greencode.petfinder.data.sources.location.LocationFactory;
import com.greencode.petfinder.data.sources.location.LocationLocalDataSource;
import com.greencode.petfinder.data.sources.location.LocationSource;
import com.greencode.petfinder.data.sources.location.RealLocationDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

/**
 * @author Anton Kazakov
 * @date 19.05.17.
 */
public class LocationRepositoryTest {

    private LocationRepository locationRepository;

    @Mock
    LocationFactory locationFactory;

    @Mock
    LocationLocalDataSource locationLocalDataSource;

    @Mock
    RealLocationDataSource realLocationDataSource;

    @Mock
    LocationSource locationSource;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        locationRepository = new LocationRepository(locationFactory);
        when(locationFactory.createDependingOnCache()).thenReturn(locationSource);
        when(locationFactory.createLocalDataSource()).thenReturn(locationLocalDataSource);
        when(locationFactory.createCloudDataSource()).thenReturn(realLocationDataSource);
    }

    @Test
    public void getMyLocation() throws Exception {
        SimpleLocation simpleLocation = new SimpleLocation();
        when(locationFactory.createDependingOnCache().getMyLocation()).thenReturn(Observable.just(simpleLocation));

        TestSubscriber<SimpleLocation> simpleLocationTestSubscriber = new TestSubscriber<>();
        locationRepository.getMyLocation().subscribe(simpleLocationTestSubscriber);
        simpleLocationTestSubscriber.awaitTerminalEvent();

        simpleLocationTestSubscriber.assertCompleted();
        simpleLocationTestSubscriber.assertNoErrors();
        verify(locationFactory.createDependingOnCache());
        assertEquals(simpleLocation, simpleLocationTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void getMyRealLocation() throws Exception {
        SimpleLocation simpleLocation = new SimpleLocation();
        when(locationFactory.createCloudDataSource().getMyLocation()).thenReturn(Observable.just(simpleLocation));

        TestSubscriber<SimpleLocation> simpleLocationTestSubscriber = new TestSubscriber<>();
        locationRepository.getMyRealLocation().subscribe(simpleLocationTestSubscriber);
        simpleLocationTestSubscriber.awaitTerminalEvent();

        simpleLocationTestSubscriber.assertCompleted();
        simpleLocationTestSubscriber.assertNoErrors();
        verify(locationFactory.createCloudDataSource());
        assertEquals(simpleLocation, simpleLocationTestSubscriber.getOnNextEvents().get(0));
    }

}