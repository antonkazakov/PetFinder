package com.greencode.petfinder.data.sources.pets;

import com.greencode.petfinder.data.api.ApiService;
import com.greencode.petfinder.data.cache.PetCache;
import com.greencode.petfinder.data.mappers.PetMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Anton Kazakov
 * @date 17.05.17.
 */
public class PetSourcePetFactoryTest {

    @Mock
    private PetMapper petMapper;
    @Mock
    private ApiService apiService;
    @Mock
    private PetCache petCache;

    private PetSourcePetFactory petSourcePetFactory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        petSourcePetFactory = new PetSourcePetFactory(petMapper, apiService, petCache);
    }

    @Test
    public void createLocalDataSourceTest() throws Exception {
        when(petCache.isExpired())
                .thenReturn(false);
        PetDataSource petDataSource = petSourcePetFactory.createDependingOnCache();
        assertThat(petDataSource, instanceOf(LocalPetDataSource.class));
    }

    @Test
    public void createCloudDataSourceTest() throws Exception {
        when(petCache.isExpired())
                .thenReturn(true);
        PetDataSource petDataSource = petSourcePetFactory.createDependingOnCache();
        assertThat(petDataSource, instanceOf(CloudPetDataSource.class));
    }

    @Test
    public void createDependingOnCache() throws Exception {
        when(petCache.isExpired())
                .thenReturn(true)
                .thenReturn(false);
        assertThat(petSourcePetFactory.createDependingOnCache(), instanceOf(CloudPetDataSource.class));
        assertThat(petSourcePetFactory.createDependingOnCache(), instanceOf(LocalPetDataSource.class));
    }

}