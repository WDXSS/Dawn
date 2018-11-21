package com.dawndemo.ui.ipc.socket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dawndemo.R;
import com.dawndemo.base.BaseActivity;
import com.dawndemo.ui.ipc.socket.service.SocketService;
import com.dawndemo.util.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <uses-permission android:name="android.permission.INTERNET"/>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * Created by zc on 2018/11/19
 */
public class TcpClientActivity extends BaseActivity implements View.OnClickListener {
    private static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    private static final int MESSAGE_SOCKET_CONNECTED = 2;
    private static final String TAG = "TcpClientActivity" + "Socket";

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG: {
                    mMessageTextView.setText(mMessageTextView.getText()
                            + (String) msg.obj);
                    break;
                }
                case MESSAGE_SOCKET_CONNECTED: {
                    mSendButton.setEnabled(true);
                    break;
                }
                default:
                    break;
            }
        }
    };

    private Button mSendButton;
    private TextView mMessageTextView;
    private EditText mMessageEditText;
    private Intent mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_client);
        Log.i(TAG, "onCreate: " + MyUtils.getProcessName(this, Process.myPid()));

        mMessageTextView = findViewById(R.id.msg_container);
        mSendButton = findViewById(R.id.send);
        mSendButton.setOnClickListener(this);

        mMessageEditText = findViewById(R.id.msg);
        mService = new Intent(this, SocketService.class);
        startService(mService);
        new Thread() {
            @Override
            public void run() {
                connectTcpServer();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(mService);
    }

    private void connectTcpServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                Log.i(TAG, "connectTcpServer: 建立连接");
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //接收服务器消息
        try {
            Log.i(TAG, "connectTcpServer: 接收server消息");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!TcpClientActivity.this.isFinishing()) {
                String msg = br.readLine();
                System.out.println("receive:" + msg);
                if (msg != null) {
                    String time = formatDateTime(System.currentTimeMillis());
                    final String showedMsg = "server " + time + ":" + msg
                            + "\n";
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg)
                            .sendToTarget();
                }
            }
            System.out.println("quit...");
            mPrintWriter.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == mSendButton) {
            final String msg = mMessageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                mPrintWriter.println(msg);
                mMessageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                final String showedMsg = "self " + time + ":" + msg + "\n";
                mMessageTextView.setText(mMessageTextView.getText() + showedMsg);
            }
        }
    }

    private String formatDateTime(long time) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(time));
    }


    private static class MyHandler extends Handler {
        private final WeakReference<TcpClientActivity> mActivity;

        public MyHandler(TcpClientActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            TcpClientActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case MESSAGE_RECEIVE_NEW_MSG: {
                        activity.mMessageTextView.setText(activity.mMessageTextView.getText()
                                + (String) msg.obj);
                        break;
                    }
                    case MESSAGE_SOCKET_CONNECTED: {
                        activity.mSendButton.setEnabled(true);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }
}
