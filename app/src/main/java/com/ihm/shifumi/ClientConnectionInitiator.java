package com.ihm.shifumi;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by ASUS on 1/26/2018.
 */

public class ClientConnectionInitiator extends AsyncTask<Object, Integer, Socket> {

    private TextView tvHim;
    private Activity activity;
    @Override
    protected Socket doInBackground(Object[] objects) {

        String mAddress = objects[0].toString();
        int mPort = (Integer) objects[1];
        tvHim = (TextView) objects[2];
        activity = (Activity) objects[3];

        Socket mSocket = null;

        try {
            Log.d("ClientConnection", "Client 1");
             mSocket = new Socket();
            mSocket.bind(null);
            mSocket.connect(new  InetSocketAddress(mAddress,mPort), 500);
            Log.d("ClientConnection", "Client 2");


            DataInputStream inputStream = new DataInputStream(mSocket.getInputStream());
            String s = inputStream.readUTF();

            Log.d("ClientConnection", "response :"+s);

        } catch (IOException e) {
            Log.e(WiFiDirectActivity.TAG, e.getMessage());
        }

        return mSocket;
    }

    protected void onPostExecute(Socket socket) {
        new MessageReceiver().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, socket, tvHim, activity);
    }

}
