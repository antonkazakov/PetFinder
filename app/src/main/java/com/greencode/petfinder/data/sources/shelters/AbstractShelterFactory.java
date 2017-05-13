package com.greencode.petfinder.data.sources.shelters;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface AbstractShelterFactory<LOCAL extends LocalShelterDataSource, CLOUD extends CloudShelterDataSource> {

    ShelterDataSource createDependingOnCache();

    LOCAL createLocalShelterDataSource();

    CLOUD createCloudShelterDataSource();

}
