// IOnNewBookArrivedListener.aidl
package cn.steve.ipc.aidl.observer;

import cn.steve.ipc.aidl.observer.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
     void onNewBookArrived(in Book newBook);
}
