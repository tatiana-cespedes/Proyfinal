package com.cespedes.tatiana.proyfinal;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //private ViewPager mViewPager;

    LinearLayout Protoboard;
    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Protoboard = (LinearLayout) findViewById( R.id.Protoboard );

        textView = (TextView) findViewById( R.id.texto );
        textView.setText("X: ,Y: ");//texto inicial

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.TabListener tabListener= new ActionBar.TabListener(){

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab= actionBar.newTab().setTabListener(tabListener).setText("Archivo");
        actionBar.addTab(tab);

        tab= actionBar.newTab().setTabListener(tabListener).setText("Cable");
        actionBar.addTab(tab);

        tab= actionBar.newTab().setTabListener(tabListener).setText("Circuitos");
        actionBar.addTab(tab);


        Protoboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {

                stringBuilder.setLength(0);
                //si la acción que se recibe es de movimiento
                if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    stringBuilder.append("Moviendo, X:" + arg1.getX() + ", Y:" + arg1.getY());
                } else {
                    stringBuilder.append("Detenido, X:" + arg1.getX() + ", Y:" + arg1.getY());
                }
                //Se muestra en pantalla
                textView.setText(stringBuilder.toString());
                return true;
            }
        });



    }
}
