package com.example.lesson7_1423_1_7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        SharedPreferences sharedPreferences = getSharedPreferences(Settings.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        Settings.isDeleteFragmentBeforeAdd = sharedPreferences
                .getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD, false);
        Settings.isBackIsRemoveFragment = sharedPreferences
                .getBoolean(Settings.IS_BACK_IS_REMOVE_FRAGMENT, false);
        Settings.isBackStackUsed = sharedPreferences
                .getBoolean(Settings.IS_BACK_STACK_USED, false);
        Settings.isReplaceFragment = sharedPreferences
                .getBoolean(Settings.IS_REPLACE_FRAGMENT, false);
        Settings.isAddFragment = sharedPreferences
                .getBoolean(Settings.IS_ADD_FRAGMENT, false);
    }

    private void initView() {
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonMain = findViewById(R.id.button_main);
        Button buttonFavorite = findViewById(R.id.button_favorite);
        Button buttonSettings = findViewById(R.id.button_settings);

        buttonBack.setOnClickListener(this);
        buttonMain.setOnClickListener(this);
        buttonFavorite.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_back:


                FragmentManager fragmentManager = getSupportFragmentManager();
                if (Settings.isBackIsRemoveFragment){
                   Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
                   if (fragmentForDelete != null){
                       fragmentManager.beginTransaction().remove(fragmentForDelete).commit();
                   }
                } else {
                    fragmentManager.popBackStack();
                }




                break;
            case R.id.button_main:
                showFragment(MainFragment.newInstance());
                break;
            case R.id.button_favorite:
                showFragment(FavoriteFragment.newInstance());
                break;
            case R.id.button_settings:
                showFragment(SettingsFragment.newInstance());
                break;
        }
    }


    Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fragmentList = fragmentManager.getFragments(); // получаем список всех фрагментов
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    private void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (Settings.isDeleteFragmentBeforeAdd) {
            Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
            if (fragmentForDelete != null) {
                fragmentTransaction.remove(fragmentForDelete);
            }
        }

        if (Settings.isAddFragment) {
            fragmentTransaction
                    .add(R.id.fragment_container, fragment);


        } else if (Settings.isReplaceFragment) {
            fragmentTransaction
                    .replace(R.id.fragment_container, fragment);
        }

        if (Settings.isBackStackUsed) {
            fragmentTransaction.addToBackStack("");
        }

        fragmentTransaction.commit();

    }


}