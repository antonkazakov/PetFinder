package com.greencode.petfinder.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public class ActivityUtils {

    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,
                                             @IdRes int frameId,
                                             boolean needStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(frameId, fragment);
        if (needStack){
            //fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }


    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment,
                                                 @IdRes int frameId,
                                                 boolean needStack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment);
        if (needStack){
            //fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

}
