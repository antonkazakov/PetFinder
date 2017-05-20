package com.greencode.petfinder;

import android.app.Application;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.data.AppModule;
import com.greencode.petfinder.data.DaggerAppComponent;
import com.greencode.petfinder.data.NetworkModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Anton Kazakov
 * @date 29.03.17.
 */

public class PFApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        appComponent = buildComponent();
    }

    public AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://api.petfinder.com"))
                .build();
    }

    public void initRealm(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(11)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}
