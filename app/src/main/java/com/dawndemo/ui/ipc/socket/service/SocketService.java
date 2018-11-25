package com.dawndemo.ui.ipc.socket.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dawndemo.util.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by zc on 2018/11/19
 */
public class SocketService extends Service {
    private static final String TAG = "SocketService";

    private boolean mIsServiceDestroyed = false;
    private String[] mDefinedMessages = new String[]{
            "你好， hello world",
            "请问你的名字",
            "今天北京天气不错",
            "你知道吗，我可以多人聊天",
            "给你讲个笑话吧"
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " +MyUtils.getProcessName(this,Process.myPid()));
        new Thread(new TcpServer()).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestroyed = true;
        Log.i(TAG, "onDestroy: ");
    }


    //创建一个线程，链接客户端
    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("establish tcp server failed , port :8688");
                return;
            }

            //接收客户端的请求
            while (!mIsServiceDestroyed) {
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");

                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void responseClient(Socket client) throws IOException {
            //用于读取客户端信息
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            //用于向客户端发送消息
            PrintWriter out;
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            out.println("欢迎来到聊天室");

            while (!mIsServiceDestroyed) {
                String str = in.readLine();
                System.out.println("msg from client :" + str);
                if (str == null) {
                    //客户端端口链接
                    return;
                }
                int i = new Random().nextInt(mDefinedMessages.length);
                String msg = mDefinedMessages[i];
                out.println(msg);
                System.out.println("send : " + msg);
            }
            System.out.println("client quit .");
            in.close();
            out.close();
            client.close();
        }

    }
}
