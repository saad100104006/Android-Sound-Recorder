package com.autocallrecorder.hidepapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Created by HP on 10/9/2017.
 */


    public class OutgoingCallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract phone number reformatted by previous receivers
            String phoneNumber = getResultData();
            if (phoneNumber == null) {
                // No reformatted number, use the original
                phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            }

            if(phoneNumber.equals("#0123")){ // DialedNumber checking.
                // My app will bring up, so cancel the broadcast
                setResultData(null);

                PackageManager p = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, SplashActivity.class);
                p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

                // Start my app
                Intent i=new Intent(context,SplashActivity.class);
                i.putExtra("extra_phone", phoneNumber);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

        }

    }
