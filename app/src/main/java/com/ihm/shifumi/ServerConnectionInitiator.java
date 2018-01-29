package com.ihm.shifumi;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ASUS on 1/26/2018.
 */

public class ServerConnectionInitiator extends AsyncTask<Object, Integer, Socket>{

    private TextView tvHim;
    private Activity activity;
    @Override
    protected Socket doInBackground(Object[] objects) {

        Socket client = null;
        tvHim = (TextView) objects[0];
        activity = (Activity) objects[1];
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            Log.d("ServerConnectio", "Server 1");
            ServerSocket serverSocket = new ServerSocket(8888);
            Log.d("ServerConnectio", "Server 2");
            client = serverSocket.accept();

            DataOutputStream mDataOutputStream = new DataOutputStream(client.getOutputStream());
            mDataOutputStream.writeUTF("Hello i'm the server");

            serverSocket.close();

        } catch (IOException e) {
            Log.e(WiFiDirectActivity.TAG, e.getMessage());
        }
        return client;
    }

    protected void onPostExecute(Socket socket) {
        new MessageReceiver().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, socket, tvHim, activity);
    }

}
