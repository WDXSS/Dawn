// IBookManager.aidl
package com.dawndemo;
import com.dawndemo.ui.ipc.aidl.entity.Book;
import com.dawndemo.ui.ipc.aidl.IOnNewBookArrivedListener;

//aidl 文件可以自定义路径：aidl.srcDirs = ['src/main/java']
//imort 引入需要的路径
interface IBookManager {
    List<Book> getBookList();
    //必须指明参数方向：in 输入型参数，out 输出型参数 ，inout输入输出型参数
    void addBook(in Book book);
     //新书的监听
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}
