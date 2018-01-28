package com.ihm.shifumi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ihm.shifumi.listeners.GameButtonListener;
import com.ihm.shifumi.listeners.LaunchListener;
import com.ihm.shifumi.players.ClientPlayer;
import com.ihm.shifumi.players.Player;
import com.ihm.shifumi.players.ServerPlayer;

public class GameActivity extends Activity {

    private Player player;
    private boolean server;

    Button btn_play;

    ImageButton btn_scissors;
    ImageButton btn_stone;
    ImageButton btn_paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        TextView tv_status = findViewById(R.id.tv_status);

        server = this.getIntent().getExtras().getBoolean("am_I_the_server");
        if(this.getIntent().getExtras().getBoolean("am_I_the_server")) {
            player = new ServerPlayer(this);
            tv_status.setText("I'm the server");
        }
        else {
            player = new ClientPlayer(this);
            tv_status.setText("I'm the client");
        }

        // BUTTONS
         btn_scissors = findViewById(R.id.btn_ciseaux);
         btn_stone = findViewById(R.id.btn_cailloux);
         btn_paper = findViewById(R.id.btn_feuille);

         btn_play = findViewById(R.id.btn_play);

        btn_paper.setOnClickListener(new GameButtonListener("paper", player));
        btn_stone.setOnClickListener(new GameButtonListener("stone", player));
        btn_scissors.setOnClickListener(new GameButtonListener("coup-coup", player));

        btn_play.setOnClickListener(new LaunchListener(player ));

        updateButtonEnabled(false);
        player.setReady();
    }



    public void updateButtonEnabled(boolean activated){
        btn_paper.setEnabled(activated);
        btn_scissors.setEnabled(activated);
        btn_stone.setEnabled(activated);
    }
}
