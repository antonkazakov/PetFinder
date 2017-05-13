package com.greencode.petfinder.data.sources.location;

/**
 * @author Anton Kazakov
 * @date 14.04.17.
 */

public interface AbstractLocationFactory<LOCAL extends LocationSource, CLOUD extends LocationSource> {

    LocationSource createDependingOnCache();

    LOCAL createLocalDataSource();

    CLOUD createCloudDataSource();

}
