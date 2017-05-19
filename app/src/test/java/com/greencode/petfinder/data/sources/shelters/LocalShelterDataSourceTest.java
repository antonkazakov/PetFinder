package com.greencode.petfinder.data.sources.shelters;

import com.greencode.petfinder.data.cache.ShelterCache;
import com.greencode.petfinder.data.entity.locanbeans.shelter.Shelter;

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
public class LocalShelterDataSourceTest {

    @Mock
    private ShelterCache shelterCache;

    private LocalShelterDataSource localShelterDataSource;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        localShelterDataSource = new LocalShelterDataSource(shelterCache);
    }

    @Test
    public void getShelters() throws Exception {
        List<Shelter> shelters = fakeShelters();
        //given
        when(shelterCache.getAll()).thenReturn(Observable.just(shelters));
        TestSubscriber<List<Shelter>> shelterTestSubscriber = new TestSubscriber<>();
        localShelterDataSource.getShelters(new HashMap<>()).subscribe(shelterTestSubscriber);

        shelterTestSubscriber.assertCompleted();
        shelterTestSubscriber.assertNoErrors();
        assertEquals(shelters, shelterTestSubscriber.getOnNextEvents().get(0));

    }

    @Test
    public void getShelter() throws Exception {
        Shelter shelter = new Shelter();
        //given
        when(shelterCache.get(anyString())).thenReturn(Observable.just(shelter));
        TestSubscriber<Shelter> shelterTestSubscriber = new TestSubscriber<>();
        localShelterDataSource.getShelter(new HashMap<>()).subscribe(shelterTestSubscriber);

        shelterTestSubscriber.assertCompleted();
        shelterTestSubscriber.assertNoErrors();
        assertEquals(shelter, shelterTestSubscriber.getOnNextEvents().get(0));
    }


    private List<Shelter> fakeShelters(){
        return new ArrayList<Shelter>() {{
                add(new Shelter());
                add(new Shelter());
            }};
    }

}