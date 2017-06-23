package com.greencode.petfinder.domain;

import com.greencode.petfinder.data.repository.PetRepository;

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
public class SearchPetsInteractorTest {

    @Mock
    PetRepository petRepository;

    private SearchPetsInteractor searchPetsInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchPetsInteractor = new SearchPetsInteractor(
                Schedulers.immediate(),
                Schedulers.immediate(),
                petRepository);
    }

    @Test
    public void buildObservable() throws Exception {
        searchPetsInteractor.buildObservable(new HashMap<>());
        Mockito.verify(petRepository).findPet(Mockito.anyMap());
    }


}