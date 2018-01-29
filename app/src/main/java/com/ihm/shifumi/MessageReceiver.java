package com.ihm.shifumi;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by ASUS on 1/26/2018.
 */

public class MessageReceiver extends AsyncTask<Object, Integer, String>{
    
    @Override
    protected String doInBackground(Object[] objects) {

        Socket socket = (Socket) objects[0];
        final TextView tvHim = (TextView) objects[1];
        Activity activity = (Activity) objects[2];
        try {

            while(true){
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                final Action message = Action.valueOf(inputStream.readInt());
                Log.d("MessageReceiver", String.valueOf(message));
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        tvHim.setText("Him : "+message.getStringValue());

                    }
                });

            }

        } catch (IOException e) {
            Log.e("MessageReceiver", e.getMessage());
            return null;
        }
    }
}
