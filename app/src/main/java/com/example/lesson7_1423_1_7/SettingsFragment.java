package com.example.lesson7_1423_1_7;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;


public class SettingsFragment extends Fragment {

//    RadioButton radioButtonAdd;
//    RadioButton radioButtonReplace;
//    SwitchCompat switchBackStack;
//    SwitchCompat switchBackIsRemove;
//    SwitchCompat switchDelBeforeAdd;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(v);

        return v;
    }

    private void initView(View v) {
        RadioButton radioButtonAdd = v.findViewById(R.id.radio_button_add);
        radioButtonAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Settings.isReplaceFragment = isChecked;
                writeSettings();
            }
        });

        RadioButton radioButtonReplace = v.findViewById(R.id.radio_button_replace);
        radioButtonReplace.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Settings.isReplaceFragment = isChecked;
                writeSettings();

            }
        });

        SwitchCompat switchBackStack = v.findViewById(R.id.switch_back_stack);
        switchBackStack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Settings.isBackStackUsed = b;
                writeSettings();
            }
        });

        SwitchCompat switchBackIsRemove = v.findViewById(R.id.switch_back_is_remove);
        switchBackIsRemove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Settings.isBackIsRemoveFragment = b;
                writeSettings();
            }
        });

        SwitchCompat switchDelBeforeAdd = v.findViewById(R.id.switch_del_before_add);
        switchDelBeforeAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Settings.isDeleteFragmentBeforeAdd = b;
                writeSettings();
            }
        });


    }

    private void writeSettings() {
        SharedPreferences sharedPreferences = requireActivity()
                .getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Settings.IS_ADD_FRAGMENT, Settings.isAddFragment);
        editor.putBoolean(Settings.IS_REPLACE_FRAGMENT, Settings.isReplaceFragment);
        editor.putBoolean(Settings.IS_BACK_STACK_USED, Settings.isBackStackUsed);
        editor.putBoolean(Settings.IS_BACK_IS_REMOVE_FRAGMENT, Settings.isBackIsRemoveFragment);
        editor.putBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, Settings.isDeleteFragmentBeforeAdd);
        editor.apply();
    }


}