// IOnNewBookArrivedListener.aidl
package com.dawndemo.ui.ipc.aidl;
import com.dawndemo.ui.ipc.aidl.entity.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
    //新增book的监听
   void onNewBookArrived(in Book newBook);
}
