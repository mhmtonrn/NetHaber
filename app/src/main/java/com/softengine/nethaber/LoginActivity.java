package com.softengine.nethaber;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.softengine.nethaber.fragment.Dunya;
import com.softengine.nethaber.fragment.Ekonomi;
import com.softengine.nethaber.fragment.Magazin;
import com.softengine.nethaber.fragment.Spor;
import com.softengine.nethaber.fragment.TumHaberler;
import com.softengine.nethaber.model.NewsModel;
import com.softengine.nethaber.network.GetNews;
import com.softengine.nethaber.network.NewsListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Haber Türk");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.listFragment, new Magazin(getApplicationContext())).commit();
        actionBar.setSubtitle(R.string.title_tumhaberler);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_gundem:
                    ft.replace(R.id.listFragment, new TumHaberler(getApplicationContext())).commit();
                    actionBar.setSubtitle(R.string.title_tumhaberler);
                    return true;
                case R.id.navigation_ekonomi:
                    actionBar.setSubtitle(R.string.title_ekomomi);
                    ft.replace(R.id.listFragment, new Ekonomi(getApplicationContext())).commit();
                    return true;
                case R.id.navigation_dunya:
                    actionBar.setSubtitle(R.string.title_dunya);
                    ft.replace(R.id.listFragment, new Dunya(getApplicationContext())).commit();
                    return true;
                case R.id.navigation_spor:
                    actionBar.setSubtitle(R.string.title_spor);
                    ft.replace(R.id.listFragment, new Spor(getApplicationContext())).commit();
                    return true;
                case R.id.navigation_magazin:
                    actionBar.setSubtitle(R.string.title_magazin);
                    ft.replace(R.id.listFragment, new Magazin(getApplicationContext())).commit();
                    return true;

            }

            return false;
        }

    };

}
