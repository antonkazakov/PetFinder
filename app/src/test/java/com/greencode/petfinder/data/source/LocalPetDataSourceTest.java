package com.greencode.petfinder.data.source;

import com.greencode.petfinder.data.cache.PetCache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * @author Anton Kazakov
 * @date 08.04.17.
 */
public class LocalPetDataSourceTest {

    @Mock
    PetCache petCache;

    private static final String GET_PET_RESPONSE = "/Users/antonkazakov/android/PetFinder/app/src/test/resources/xml/petfind.xml";

    @Before
    public void setUp() throws Exception {
        petCache = new PetCache();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPets(){
        //Mockito.when(petCache.get(Mockito.anyString()))
         //       .thenReturn()
    }

    @After
    public void tearDown() throws Exception {

    }

}