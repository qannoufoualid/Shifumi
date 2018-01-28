package com.ihm.shifumi.players;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.ihm.shifumi.GameActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by delemazu3u on 26/01/2018.
 */

public class ClientPlayer implements Player {

    Socket socket;

    private GameActivity activity;

    public ClientPlayer(GameActivity activity){
        this.activity = activity;
    }



    @Override
    public void setReady() {
    }

    @Override
    public void sendMessage() {
        SendMessage lol = new SendMessage();
        lol.execute();
    }

    //==============================================================

    public static class SendMessage extends AsyncTask {
        Socket socket;

        public SendMessage(){

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                socket = new Socket();
                socket.bind(null);
                socket.connect((new InetSocketAddress("server", 8888)), 500);

                /**
                 * Create a byte stream from a JPEG file and pipe it to the output stream
                 * of the socket. This data will be retrieved by the server device.
                 */
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(1);
                Log.i("CLIENT", "J'ai envoyé un truc");
                outputStream.close();
                return true;
            } catch (FileNotFoundException e) {
                return false;
            } catch (IOException e) {
                return false;

            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            //catch logic
                        }
                    }
                }
            }
        }
        }//fin SendMessage
}
