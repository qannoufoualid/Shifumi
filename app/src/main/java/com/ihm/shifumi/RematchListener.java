package com.ihm.shifumi;

import android.os.AsyncTask;
import android.view.View;

/**
 * Created by delemazu3u on 29/01/2018.
 */

class RematchListener implements View.OnClickListener {

    private GameActivity activity;


    public RematchListener(GameActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        activity.reset();
        new MessageSender().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,activity.getSocket(),Action.REPLAY);
    }
}
