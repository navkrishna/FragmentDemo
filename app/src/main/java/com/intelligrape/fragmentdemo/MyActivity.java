package com.intelligrape.fragmentdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity implements FragmentOne.Intercom, FragmentTwo.Intercom, FragmentThree.Intercom {

    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager manager;
        FragmentTransaction transaction;

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        FragmentOne f1 = new FragmentOne();
        f1.setIntercom(this);
        transaction.add(R.id.container, f1, "frag1");
        transaction.commit();
    }

    @Override
    public void msgFromFragOne(int index) {
        currentIndex = index;

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentTwo f2 = new FragmentTwo();
        f2.setIntercom(this);

        transaction.add(R.id.container, f2, "frag2");
        transaction.addToBackStack("frag2Add");
        transaction.commit();
    }

    @Override
    public void msgFromFragTwo(String message) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentThree f3 = new FragmentThree();
        f3.setIntercom(this);
        transaction.add(R.id.container, f3, "frag3");
        transaction.addToBackStack("frag3Add");
        transaction.commit();

        f3.prevMessage(message);
    }

    @Override
    public void msgFromFragThree(String message) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FragmentOne f1 = (FragmentOne) manager.findFragmentByTag("frag1");
        FragmentThree f3 = (FragmentThree) manager.findFragmentByTag("frag3");
        transaction.detach(f3);
        manager.popBackStack("frag2Add", 1);

        if (f1 != null) {
            f1.updateMessage(message, currentIndex);
        }
        else {
            Toast.makeText(MyActivity.this, "Fragment not found", Toast.LENGTH_LONG).show();
            f1 = new FragmentOne();
            transaction.add(f1, "frag1");
        }
        transaction.commit();
    }
}