package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {

    private long startTime = 20 * 1000; // 20 Sec Idle Time
    private final long interval = 1 * 1000;
    private LogoutTimerTask logoutTimerTask;
    private boolean isOnScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logoutTimerTask = new LogoutTimerTask(startTime, interval);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
     /*   if (this instanceof MainActivity) {
            stopTimerTask();
        } else {
            startTimerTask();
        }*/
    }

    protected void startTimerTask() {
        logoutTimerTask.cancel();
        logoutTimerTask.start();
    }

    protected void stopTimerTask() {
        logoutTimerTask.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnScreen = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOnScreen = true;
    }

    private void navigateToLogin() {
        if (isOnScreen) {
            startActivity(new Intent(BaseActivity.this, MainActivity.class).addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP));
            this.finish();
        } else {
            this.finish();
        }
    }

    public class LogoutTimerTask extends CountDownTimer {

        public LogoutTimerTask(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            Log.e("Finish", "onFinish: ");
            navigateToLogin();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("MilliSec", " - " + millisUntilFinished);
        }
    }
}