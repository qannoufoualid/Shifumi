package com.ihm.shifumi.players;

import com.ihm.shifumi.GameActivity;

/**
 * Created by delemazu3u on 26/01/2018.
 */

public class ClientPlayer implements Player {

    private GameActivity activity;

    public ClientPlayer(GameActivity activity){
        this.activity = activity;
    }



    @Override
    public void setReady() {

    }
}
