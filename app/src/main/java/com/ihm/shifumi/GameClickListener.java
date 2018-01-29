package com.ihm.shifumi;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.Socket;

/**
 * Created by ASUS on 1/28/2018.
 */

public class GameClickListener implements View.OnClickListener {

    private Action action;
    private Socket socket;
    private TextView tvYou;
    private GameActivity activity;

    public GameClickListener(Action action, Socket socket, TextView tvYou, GameActivity activity){
        this.action = action;
        this.socket = socket;
        this.tvYou = tvYou;
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        tvYou.setText("You : "+action.getStringValue());
        activity.setMyMove(action);
        Log.d("GameClickListener", "Sending the message ...");
        new MessageSender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,socket, action);
        activity.enableGameButton(false);
        activity.resolution();
    }
}
