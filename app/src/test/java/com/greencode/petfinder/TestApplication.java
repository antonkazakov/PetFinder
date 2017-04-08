package com.greencode.petfinder;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.data.DaggerAppComponent;

/**
 * @author Anton Kazakov
 * @date 31.03.17.
 */

public class TestApplication extends PFApplication {

    @Override
    public AppComponent buildComponent() {
        return DaggerAppComponent.builder().build();
    }

}
