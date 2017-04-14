package com.greencode.petfinder.data.source.pet;

import com.greencode.petfinder.data.cache.Cache;
import com.greencode.petfinder.data.source.pet.PetDataSource;

import javax.sql.DataSource;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface AbstractPetFactory<LOCAL extends PetDataSource, CLOUD extends PetDataSource> {

    PetDataSource createDependingOnCache();

    LOCAL createLocalDataSource();

    CLOUD createCloudDataSource();

}
