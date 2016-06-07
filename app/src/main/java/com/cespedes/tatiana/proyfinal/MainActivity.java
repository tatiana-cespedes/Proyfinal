package com.cespedes.tatiana.proyfinal;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    matrices[][] ABCDE;
    matrices[][] FGHIJ;
    matrices[][] GND;
    ImageView Protoboard;
    matrices[][] VCC;
    Bitmap bitmap;
    Canvas canvas;
    float downx = 0;
    float downy = 0;
    float height;
    matrices lugar;
    double minx = 1200;
    double miny = 1200;
    Paint paint;
    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;
    float upx = 0;
    float upy = 0;
    float width;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Protoboard = (ImageView)findViewById(R.id.protoboard);
        textView = (TextView)findViewById(R.id.texto);
        textView.setText("X: ,Y: ");
        ABCDE = new matrices[63][5];
        FGHIJ = new matrices[63][5];
        VCC = new matrices[50][2];
        GND = new matrices[50][2];
        lugar = new matrices();
        inicializar_matrices();
        bitmap = Bitmap.createBitmap(1050, 345, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(this.bitmap);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6.0F);
        Protoboard.setBackgroundResource(R.drawable.protoboard);
        Protoboard.setImageBitmap(this.bitmap);



        for (int i = 0; i < 63; i++) {
            for (int m = 0; m < 5; m++) {
                canvas.drawPoint((float) ABCDE[i][m].getCoordenada_x(), (float) ABCDE[i][m].getCoordenada_y(), paint);
                canvas.drawPoint((float) FGHIJ[i][m].getCoordenada_x(), (float) FGHIJ[i][m].getCoordenada_y(), paint);
            }
        }

        for (int j = 0; j < 50; j++) {
            for (int k = 0; k < 2; k++) {
                canvas.drawPoint((float) VCC[j][k].getCoordenada_x(), (float) VCC[j][k].getCoordenada_y(), paint);
                canvas.drawPoint((float) GND[j][k].getCoordenada_x(), (float) GND[j][k].getCoordenada_y(), paint);
            }
        }





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



    }


    public void inicializar_matrices()
    {
        double distancia= 16.15, distanciay=294.5;
        double disx = 23.97, disy1= 194.9, disy2=80.9;
        double disy3 = disx + (distancia*2);
        double disy4= 15;
        double disy5 = disy4 + distancia;
        double suma = disy3;

        for (int i = 0; i < 63; i++) {
            for (int m = 0; m < 5; m++) {
                ABCDE[i][m] = new matrices();
                FGHIJ[i][m] = new matrices();
                ABCDE[i][m].setCoordenada_x(disx + (distancia * i));
                ABCDE[i][m].setCoordenada_y(disy1 + (distancia * m));
                FGHIJ[i][m].setCoordenada_x(disx + (distancia * i));
                FGHIJ[i][m].setCoordenada_y(disy2 + (distancia * m));
            }
        }
        for(int j = 0; j < 50; j++) {
            for (int k = 0; k < 2; k++) {
                VCC[j][k] = new matrices();
                GND[j][k] = new matrices();
                VCC[j][k].setCoordenada_x(suma);
                VCC[j][k].setCoordenada_y(disy5 + distanciay * k);
                GND[j][k].setCoordenada_x(suma);
                GND[j][k].setCoordenada_y(disy4 + distanciay * k);
            }
            if ((j + 1) % 5 == 0) {
                suma += 2 * distancia;
            } else {
                suma += distancia;
            }
        }
    }

}
