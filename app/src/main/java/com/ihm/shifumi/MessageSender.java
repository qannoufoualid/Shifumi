package com.ihm.shifumi;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by ASUS on 1/26/2018.
 */

public class MessageSender extends AsyncTask<Object, Integer, Integer>{

    @Override
    protected Integer doInBackground(Object[] objects) {

        Socket socket = (Socket) objects[0];
        Action message = (Action) objects[1];

        try {
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            Log.d("MessageSender", "message supposed to be sent!");
            outputStream.writeInt(message.getValue());

        } catch (IOException e) {
            Log.e(WiFiDirectActivity.TAG, e.getMessage());
            return 0;
        }

        return 1;
    }
}
