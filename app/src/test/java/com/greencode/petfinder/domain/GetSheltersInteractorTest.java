package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.repository.LocationRepository;
import com.greencode.petfinder.data.repository.SheltersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import rx.schedulers.Schedulers;

/**
 * @author Anton Kazakov
 * @date 23.06.17.
 */
public class GetSheltersInteractorTest {

    @Mock
    SheltersRepository sheltersRepository;

    @Mock
    LocationRepository locationRepository;

    private GetSheltersInteractor getSheltersInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getSheltersInteractor = new GetSheltersInteractor(
                Schedulers.immediate(),
                Schedulers.immediate(),
                locationRepository,
                sheltersRepository);
    }

    @Test
    public void buildObservable() throws Exception {
        getSheltersInteractor.buildObservable(new HashMap<>());
        Mockito.verify(sheltersRepository).getShelters(Mockito.anyMap());
    }

}