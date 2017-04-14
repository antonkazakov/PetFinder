package com.greencode.petfinder;

import android.app.Application;

import com.greencode.petfinder.data.AppComponent;
import com.greencode.petfinder.data.AppModule;
import com.greencode.petfinder.data.DaggerAppComponent;

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
                .build();
    }

    public void initRealm(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(6)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

}
