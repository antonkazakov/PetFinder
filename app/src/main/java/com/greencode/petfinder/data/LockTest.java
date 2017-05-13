package com.greencode.petfinder.data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Anton Kazakov
 * @date 13.05.17.
 */

public class LockTest {

    private static ReentrantLock lock = new ReentrantLock();

    public static ReentrantLock getLock() {
        return lock;
    }

}
