package com.greencode.petfinder.data.sources.pets;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface AbstractPetFactory<LOCAL extends PetDataSource, CLOUD extends PetDataSource> {

    PetDataSource createDependingOnCache();

    LOCAL createLocalDataSource();

    CLOUD createCloudDataSource();

}
