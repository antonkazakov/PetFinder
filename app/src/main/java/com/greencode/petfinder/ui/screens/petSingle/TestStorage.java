package com.greencode.petfinder.ui.screens.petSingle;

import java.util.HashMap;

/**
 * @author Anton Kazakov
 * @date 09.05.17.
 */

public class TestStorage {

    private static HashMap<String, String> map = new HashMap<>();


    public static void add(String a, String b){
        map.put(a,b);
    }


    public static String get(String a){
        return map.get(a);
    }

}
