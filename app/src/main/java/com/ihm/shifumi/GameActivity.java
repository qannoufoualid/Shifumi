package com.ihm.shifumi;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

import com.ihm.shifumi.players.ClientPlayer;
import com.ihm.shifumi.players.Player;
import com.ihm.shifumi.players.ServerPlayer;

public class GameActivity extends Activity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        TextView tvHim = (TextView) findViewById(R.id.tvHim);
        TextView tvYou = (TextView) findViewById(R.id.tvYou);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        WifiP2pInfo info = intent.getParcelableExtra("deviceInfo");


        Socket socket = null;
        try {
            if(info.isGroupOwner){

                socket = new ServerConnectionInitiator().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tvHim, this).get();

            }else{
                socket = new ClientConnectionInitiator().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, info.groupOwnerAddress.getHostAddress(), new Integer(8888), tvHim, this).get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        Button paperButton = (Button) findViewById(R.id.btnPaper);
        Button rockButton = (Button) findViewById(R.id.btnRock);
        Button scissorButton = (Button) findViewById(R.id.btnScissor);

        paperButton.setOnClickListener(new GameClickListener(Action.PAPER, socket, tvYou));
        rockButton.setOnClickListener(new GameClickListener(Action.ROCK, socket, tvYou));
        scissorButton.setOnClickListener(new GameClickListener(Action.SCISSOR, socket, tvYou));


    }
}
