package com.ihm.shifumi.listeners;

import android.net.wifi.p2p.WifiP2pManager;
import android.view.View;
import android.widget.Button;

import com.ihm.shifumi.players.Player;

/**
 * Created by Fufu on 28/01/2018.
 */

public class GameButtonListener implements View.OnClickListener {

    private String message;
    private Player player;

    public GameButtonListener(String message, Player player){
        this.message = message;
        this.player = player;
    }

    @Override
    public void onClick(View view) {
    }
}
