package com.dawndemo.ui.ipc.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dawndemo.IBookManager;
import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.ipc.aidl.entity.Book;

import java.util.List;

/**
 * AIDL使用是注意：<br/>
 * 1.实体类Book 必须和Book.aidl 相同的路径<br/>
 * 2.Book.aidl 必须注明序列化 parcelable Book;<br/>
 * <p>
 * 3.接口.aidl 文件 中的方法需要注明方向：in 输入型参数，out 输出型参数 ，inout输入输出型参数 <br/>
 * <p>
 * 4.添加新书监听 跨进程使用：RemoteCallbackList<Listener> list ; beginBroadcast() 和 finishBroadcast() 必须配对使用<br/>
 * 5.Binder 有可能意外死亡，1.DeathRecipient 监听 2.<br/>
 * 6.Service 绑定成功后，创建 DeathRecipient 监听：linkToDeath 和 unlinkToDeath<br/>
 * <p>
 * 7.Binder 使用的是线程池，主线程调用是，注意ANR <br/>
 * <p>
 * 8.aidl 文件可以自定义路径：aidl.srcDirs = ['src/main/java'] <br/>
 * <p>
 * Created by zc on 2018/11/21
 */
public class AidlMainActivity extends BaseActivity {
    private static final String TAG = "AidlMainActivity" + "aidl";
    private IBookManager mRemoteBookManager;
    private int bookCount = 0;
    private Intent mServiceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_aidl);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager bookManager = IBookManager.Stub.asInterface(service);
            mRemoteBookManager = bookManager;
            try {
                //TODO 建立DeathRecipient 监听
                mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IOnNewBookArrivedListener mIOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.d(TAG, "onNewBookArrived: 新增book = " + newBook.toString());
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binder died. t name:" + Thread.currentThread().getName());
            if (mRemoteBookManager == null)
                return;
            //TODO Binder 被销毁
            mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mRemoteBookManager = null;
            // TODO:这里重新绑定远程Service
            bindService(mServiceIntent, mConnection, BIND_AUTO_CREATE);
        }
    };

    public void binderBookService(View view) {
        mServiceIntent = new Intent();
        mServiceIntent.setClass(this, BookManagerService.class);
        bindService(mServiceIntent, mConnection, BIND_AUTO_CREATE);
    }

    public void addBookToService(View view) {
        Log.d(TAG, "addBookToService: 添加一本书");
        Book book = new Book();
        book.bookId = bookCount++;
        book.bookName = "开发与艺术 " + bookCount;
        try {
            mRemoteBookManager.addBook(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getBookList(View view) {
        try {
            List<Book> list = mRemoteBookManager.getBookList();
            for (int i = 0; list != null && i < list.size(); i++) {
                Book book = list.get(i);
                Log.d(TAG, "getBookList: book = " + book.toString());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void registerListen(View view) {
        Log.d(TAG, "registerListen: 注册新书监听 ");
        try {
            mRemoteBookManager.registerListener(mIOnNewBookArrivedListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unRegisterListen(View view) {
        try {
            Log.d(TAG, "unRegisterListen: 取消新书监听 ");
            mRemoteBookManager.unregisterListener(mIOnNewBookArrivedListener);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {

        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "unregister listener:" + mIOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mIOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        if (mConnection != null) {
            unbindService(mConnection);
            mConnection = null;
        }
        super.onDestroy();
    }

}
