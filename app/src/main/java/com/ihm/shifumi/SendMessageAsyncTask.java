package com.ihm.shifumi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ASUS on 1/26/2018.
 */

public class SendMessageAsyncTask extends AsyncTask {

    private Context context;

    public SendMessageAsyncTask(Context context, View statusText) {
        this.context = context;

    }

    @Override
    protected String doInBackground(Object[] params) {
        try {

            /**
             * Create a server socket and wait for client connections. This
             * call blocks until a connection is accepted from a client
             */
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket client = serverSocket.accept();

            /**
             * If this code is reached, a client has connected and transferred data
             * Save the input stream from the client as a JPEG file
             */
            String message = "Hello from the other side!!";

            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());

            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
            serverSocket.close();
            return f.getAbsolutePath();
        } catch (IOException e) {
            Log.e(WiFiDirectActivity.TAG, e.getMessage());
            return null;
        }
    }

    /**
     * Start activity that can handle the JPEG image
     */
    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            statusText.setText("File copied - " + result);
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + result), "image/*");
            context.startActivity(intent);
        }
    }
}
