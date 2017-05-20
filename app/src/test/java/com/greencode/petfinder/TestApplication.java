package com.greencode.petfinder;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.data.AppModule;
import com.greencode.petfinder.data.DaggerAppComponent;
import com.greencode.petfinder.data.NetworkModule;
import com.greencode.petfinder.data.NetworkTestModule;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * @author Anton Kazakov
 * @date 20.05.17.
 */
public class TestApplication extends PFApplication {

    @Override
    public AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkTestModule(""))
                .build();
    }


    @Override
    public void initRealm() {

    }
}
