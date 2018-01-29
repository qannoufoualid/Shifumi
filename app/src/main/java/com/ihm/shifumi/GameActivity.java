package com.ihm.shifumi;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

import com.ihm.shifumi.players.Player;

public class GameActivity extends Activity {


    private TextView tvHim ;
    private TextView tvYou;
    private TextView tvResult;

    private Socket socket;

    Button paperButton;
    Button rockButton;
    Button scissorButton;
    private Button btn_rematch;

    private Player player;

    private Action my_move;
    private Action his_move;

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

        btn_rematch = findViewById(R.id.btn_rematch);
        btn_rematch.setEnabled(false);
        btn_rematch.setVisibility(View.GONE);
        btn_rematch.setOnClickListener(new RematchListener(this));

        tvResult = findViewById(R.id.tvResult);
        tvHim = (TextView) findViewById(R.id.tvHim);
        tvYou = (TextView) findViewById(R.id.tvYou);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        WifiP2pInfo info = intent.getParcelableExtra("deviceInfo");


        socket = null;
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



        paperButton = findViewById(R.id.btnPaper);
        rockButton = findViewById(R.id.btnRock);
        scissorButton = findViewById(R.id.btnScissor);



        paperButton.setOnClickListener(new GameClickListener(Action.PAPER, socket, tvYou, this));
        rockButton.setOnClickListener(new GameClickListener(Action.ROCK, socket, tvYou, this));
        scissorButton.setOnClickListener(new GameClickListener(Action.SCISSOR, socket, tvYou, this));


    }

    public void onMessageReceived(Action message){
        tvHim.setText("???");
        his_move = message;
        resolution();
    }

    public void resolution(){
        Log.i("INFO !!", "On passe dans la résolution");
        if(my_move != null && his_move !=null){
            if(my_move == Action.PAPER){//paper
                if(his_move == Action.PAPER)
                    tvResult.setText("TIE !");
                else if(his_move == Action.ROCK){
                    tvResult.setText("YOU WIN !");
                }else
                    tvResult.setText("YOU LOSE !");
            }else if(my_move == Action.ROCK){// rock
                if(his_move == Action.PAPER)
                    tvResult.setText("YOU LOSE !");
                else if(his_move == Action.ROCK){
                    tvResult.setText("TIE !");
                }else
                    tvResult.setText("YOU WIN !");
            }else if(my_move == Action.SCISSOR){ //scissors
                if(his_move == Action.PAPER)
                    tvResult.setText("YOU WIN !");
                else if(his_move == Action.ROCK){
                    tvResult.setText("YOU LOSE !");
                }else
                    tvResult.setText("TIE !");
            }else{
                Log.wtf("lol", "On est sorti de l'enum");
            }
            tvHim.setText(his_move.getStringValue());
            btn_rematch.setEnabled(true);
            btn_rematch.setVisibility(View.VISIBLE);
        }


    }

    public void setMyMove(Action myMove) {
        this.my_move = myMove;
    }

    public void enableGameButton(boolean b){
        paperButton.setEnabled(b);
        scissorButton.setEnabled(b);
        rockButton.setEnabled(b);

    }

    public void reset(){
        enableGameButton(true);
        my_move = null;
        his_move = null;
        tvHim.setText("");
        tvYou.setText("");
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
