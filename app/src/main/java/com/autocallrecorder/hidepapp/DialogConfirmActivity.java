package com.autocallrecorder.hidepapp;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.autocallrecorder.hidepapp.service.CallRecordService;

/**
 * Created by vieta on 6/9/2016.
 */
public class DialogConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm_speaker);

        AppCompatButton yesButton = (AppCompatButton) findViewById(R.id.yesBt);
        AppCompatButton noButton = (AppCompatButton) findViewById(R.id.noBt);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallRecordService.audioManager.setMode(AudioManager.MODE_IN_CALL);
                CallRecordService.audioManager.setSpeakerphoneOn(true);
                finish();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}