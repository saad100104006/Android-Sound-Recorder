package com.example.recordvoice.fragment;


import android.content.ComponentName;
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

import com.example.recordvoice.MainActivity;
import com.example.recordvoice.Model.CloudRecord;
import com.example.recordvoice.R;
import com.example.recordvoice.adapter.CloudFragmentAdapter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout,container,false);

        // For first switch button
        switchButton = (Switch) view.findViewById(R.id.switchButton);
        textView = (TextView) view.findViewById(R.id.textView);

        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    textView.setText("Hidden");

                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), MainActivity.class);
                    // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />

                    p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                } else {
                    textView.setText("No Hidden");

                    PackageManager p = getActivity().getPackageManager();
                    ComponentName componentName = new ComponentName(getActivity(), MainActivity.class);
                    p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            }
        });

        if (switchButton.isChecked()) {
            textView.setText("Hidden");
        } else {
            textView.setText("No Hidden");
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
