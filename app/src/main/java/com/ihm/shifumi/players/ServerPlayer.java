package com.ihm.shifumi.players;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ihm.shifumi.GameActivity;
import com.ihm.shifumi.WiFiDirectActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by delemazu3u on 26/01/2018.
 */

public class ServerPlayer implements Player {

    private GameActivity activity;

    public ServerPlayer(GameActivity activity){
        this.activity = activity;
    }

    @Override
    public void setReady() {
        //ReceiveMessage lol = new ReceiveMessage(activity);
    }


    //===============================================================================

    public static class ReceiveMessage extends AsyncTask {

        private GameActivity activity;

        ReceiveMessage(GameActivity activity){
            this.activity = activity;
            Log.i("THREAD !!", "La classe de reception a bien été créée");

        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Toast.makeText(activity, "Je suis arrivé ici tout seul", Toast.LENGTH_SHORT).show();
                Log.i("THREAD !!", "Le serveur est prêt à recevoir");
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket client = serverSocket.accept();

                InputStream inputstream = client.getInputStream();


                Toast.makeText(activity, inputstream.toString(), Toast.LENGTH_SHORT).show();
                return new Boolean(true);

            } catch (IOException e) {
                Log.e(WiFiDirectActivity.TAG, e.getMessage());
                return new Boolean(false);
            }
        }//fin doInBackGround
    }
}