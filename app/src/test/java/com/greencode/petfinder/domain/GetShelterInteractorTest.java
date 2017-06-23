package com.greencode.petfinder.domain;

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
public class GetShelterInteractorTest {

    @Mock
    SheltersRepository sheltersRepository;

    private GetShelterInteractor getShelterInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getShelterInteractor = new GetShelterInteractor(
                Schedulers.immediate(),
                Schedulers.immediate(),
                sheltersRepository);
    }

    @Test
    public void buildObservable() throws Exception {
        getShelterInteractor.buildObservable(new HashMap<>());
        Mockito.verify(sheltersRepository).getShelter(Mockito.anyMap());
    }


}