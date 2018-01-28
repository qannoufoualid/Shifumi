package com.ihm.shifumi.listeners;

import android.util.Log;
import android.view.View;

import com.ihm.shifumi.players.Player;

/**
 * Created by Fufu on 28/01/2018.
 */

public class LaunchListener implements View.OnClickListener {

    private Player player;

    public LaunchListener(Player player){
        this.player = player;
    }

    @Override
    public void onClick(View view) {
        player.sendMessage();
        Log.i("INFO !! ","Le bouton de démarrage a été pressé");
    }
}
