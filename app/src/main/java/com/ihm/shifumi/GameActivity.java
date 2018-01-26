package com.ihm.shifumi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ihm.shifumi.players.ClientPlayer;
import com.ihm.shifumi.players.Player;
import com.ihm.shifumi.players.ServerPlayer;

public class GameActivity extends Activity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(this.getIntent().getExtras().getBoolean("am_I_the_server"))
            player = new ServerPlayer();
        else
            player = new ClientPlayer();



    }
}
