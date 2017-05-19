package com.greencode.petfinder.data.repository;

import com.greencode.petfinder.data.entity.locanbeans.pet.Pet;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;
import com.greencode.petfinder.data.sources.shelters.CloudShelterDataSource;
import com.greencode.petfinder.data.sources.shelters.LocalShelterDataSource;
import com.greencode.petfinder.data.sources.shelters.ShelterDataSource;
import com.greencode.petfinder.data.sources.shelters.SheltersFactory;

import org.junit.After;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Anton Kazakov
 * @date 19.05.17.
 */
public class SheltersRepositoryTest {

    @Mock
    SheltersFactory sheltersFactory;

    @Mock
    ShelterDataSource shelterDataSource;

    @Mock
    CloudShelterDataSource cloudShelterDataSource;

    @Mock
    LocalShelterDataSource localShelterDataSource;

    private SheltersRepository sheltersRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        sheltersRepository = new SheltersRepository(sheltersFactory);
        when(sheltersFactory.createCloudShelterDataSource()).thenReturn(cloudShelterDataSource);
        when(sheltersFactory.createDependingOnCache()).thenReturn(shelterDataSource);
    }

    @Test
    public void getShelters() throws Exception {
        List<Shelter> shelters = fakeShelters();

        when(sheltersFactory.createDependingOnCache().getShelters(anyMap()))
                .thenReturn(Observable.just(shelters));

        TestSubscriber<List<Shelter>> shelterTestSubscriber = new TestSubscriber<>();
        sheltersRepository.getShelters(new HashMap<>()).subscribe(shelterTestSubscriber);
        shelterTestSubscriber.awaitTerminalEvent();

        shelterTestSubscriber.assertCompleted();
        shelterTestSubscriber.assertNoErrors();
        verify(sheltersFactory.createDependingOnCache());
        assertEquals(shelters, shelterTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void getShelter() throws Exception {
        Shelter shelter = new Shelter();

        when(sheltersFactory.createDependingOnCache().getShelter(anyMap()))
                .thenReturn(Observable.just(shelter));

        TestSubscriber<Shelter> shelterTestSubscriber = new TestSubscriber<>();
        sheltersRepository.getShelter(new HashMap<>()).subscribe(shelterTestSubscriber);
        shelterTestSubscriber.awaitTerminalEvent();

        shelterTestSubscriber.assertCompleted();
        shelterTestSubscriber.assertNoErrors();
        verify(sheltersFactory.createDependingOnCache());
        assertEquals(shelter, shelterTestSubscriber.getOnNextEvents().get(0));
    }

    @Test
    public void getSheltersFromCloud() throws Exception {
        List<Shelter> shelters = fakeShelters();

        when(sheltersFactory.createCloudShelterDataSource().getShelters(anyMap()))
                .thenReturn(Observable.just(shelters));

        TestSubscriber<List<Shelter>> shelterTestSubscriber = new TestSubscriber<>();
        sheltersRepository.getSheltersFromCloud(new HashMap<>()).subscribe(shelterTestSubscriber);
        shelterTestSubscriber.awaitTerminalEvent();

        shelterTestSubscriber.assertCompleted();
        shelterTestSubscriber.assertNoErrors();
        verify(sheltersFactory.createCloudShelterDataSource());
        assertEquals(shelters, shelterTestSubscriber.getOnNextEvents().get(0));
    }

    private List<Shelter> fakeShelters() {
        return new ArrayList<Shelter>() {{
            add(new Shelter());
        }};
    }

}