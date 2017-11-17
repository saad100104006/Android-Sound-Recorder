package com.autocallrecorder.hidepapp.fragment;


import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.autocallrecorder.hidepapp.Model.CloudRecord;
import com.autocallrecorder.hidepapp.R;
import com.autocallrecorder.hidepapp.SplashActivity;
import com.autocallrecorder.hidepapp.VideoPlay;
import com.autocallrecorder.hidepapp.adapter.CloudFragmentAdapter;

import java.util.ArrayList;

/**
 * Created by HP on 9/28/2017.
 */

public class SettingsFragment extends Fragment {
    private RecyclerView recyclerView;
    private CloudFragmentAdapter cloudFragmentAdapter;
    private ArrayList<CloudRecord> cloudRecordArrayList;
    Switch  switchButton;
    TextView textView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout,container,false);

        // For first switch button
        switchButton = (Switch) view.findViewById(R.id.switchButton);
        textView = (TextView) view.findViewById(R.id.textView);

        switchButton.setChecked(false);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView.setText("Hidden");

                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), SplashActivity.class);
                    // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />

                    p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);


                     preferences = getActivity().getSharedPreferences("AUTHENTICATION_FILE_NAME", getActivity().MODE_PRIVATE);
                     editor = preferences.edit();
                    editor.putString("NOTIFICATION","1");
                    editor.apply();


                    Intent i=new Intent(getActivity(), VideoPlay.class);
                    startActivity(i);
                } else {
                    textView.setText("Not Hidden");

                    editor.putString("NOTIFICATION","2");
                    editor.apply();


                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), SplashActivity.class);
                    p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            }
        });

        if (switchButton.isChecked()) {
            textView.setText("Hidden");
        } else {
            textView.setText("Not Hidden");
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LogMain","CloudResume");
      //  new LoadAllCloudFile().execute();
    }



}
