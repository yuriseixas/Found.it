package com.example.vini.foundit;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Activity2 extends AppCompatActivity {
    private SlidrInterface slidr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar acb = getActionBar();
        if(acb != null){
            acb.hide();
        }

        getSupportActionBar().hide();
        setContentView(R.layout.activity_2);
        Intent it = getIntent();
        String msg = it.getStringExtra("Resultado");
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        TextView helloTextView = (TextView) findViewById(R.id.Texto);
        helloTextView.setText(msg);
        slidr = Slidr.attach(this);
        slidr.unlock();



    }


}
