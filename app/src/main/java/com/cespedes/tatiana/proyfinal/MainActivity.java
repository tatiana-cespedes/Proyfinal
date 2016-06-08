package com.cespedes.tatiana.proyfinal;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    ImageView Protoboard;
    TextView textView;

    matrices[][] ABCDE, FGHIJ, GND, VCC;
    matrices lugar;

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    StringBuilder stringBuilder = new StringBuilder();

    float downx = 0, downy = 0;
    float upx = 0, upy = 0;
    double minx = 1200, miny = 1200;


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

        final CustomHorizontalScrollView SVhorizontal = (CustomHorizontalScrollView) findViewById(R.id.SVhorizontal);
        final CustomScrollView SVvertical = (CustomScrollView) findViewById(R.id.SVvertical);




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

        tab = actionBar.newTab().setTabListener(tabListener).setText("Circuitos");
        actionBar.addTab(tab);

        Protoboard.setOnTouchListener(new View.OnTouchListener() {
            Path path = new Path();
            long tiempo, tiempo2, cont=0;
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                stringBuilder.setLength(0);
                //si la acción que se recibe es de movimiento
                if (arg1.getAction() == MotionEvent.ACTION_DOWN){
                    tiempo = arg1.getEventTime();
                    downx = arg1.getX();
                    downy = arg1.getY();
                    path.moveTo(downx, downy);
                    distancia_minima(downx, downy);
                    //canvas.drawPoint((float)minx, (float)miny, paint);
                }

                if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
                    tiempo2 = (arg1.getEventTime() - tiempo);
                    if (tiempo2 > 500) {
                        if(cont==0) {
                            Toast.makeText(getApplicationContext(), "Cablear", Toast.LENGTH_SHORT).show();
                            cont++;
                        }
                        upx = arg1.getX();
                        upy = arg1.getY();
                        path.lineTo(upx, upy);
                        canvas.drawPath(path, paint);
                        Protoboard.invalidate();
                        SVhorizontal.setEnableScrolling(false);
                        SVvertical.setEnableScrolling(false);
                    }
                    stringBuilder.append("Moviendo, X:" + arg1.getX() + ", Y:" + arg1.getY());

                } else {
                    stringBuilder.append("Detenido, X:" + arg1.getX() + ", Y:" + arg1.getY());
                }
                if(arg1.getAction() == MotionEvent.ACTION_UP){
                    SVhorizontal.setEnableScrolling(true);
                    SVvertical.setEnableScrolling(true);
                    cont=0;
                    if(tiempo2> 500){
                        canvas.drawLine((float)minx, (float)miny, downx, downy, paint);
                        distancia_minima(upx, upy);
                        canvas.drawLine((float) minx, (float)miny, upx, upy, paint);
                        Protoboard.invalidate();  //Refresca el ImageView
                    }
                    tiempo2=0;
                }
                //Se muestra en pantalla las coordenadas
                textView.setText(stringBuilder.toString());
                return true;
             }
        });


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

    public void distancia_minima(double x1, double y1)
    {
        double minx1, miny1, miny2, miny3=1200, miny4=1200,minx3, minx4=1200;
        int i = 0, j = 0, k = 0, l1=8;
        int n1=-1, m, n;

        for (m = 0; m < 63; m++) {
            for (n = 0; n < 5; n++) {
                minx1 = Math.abs(ABCDE[m][n].getCoordenada_x() - x1);
                miny1 = Math.abs(ABCDE[m][n].getCoordenada_y() - y1);
                miny2 = Math.abs(FGHIJ[m][n].getCoordenada_y() - y1);
                if(m<50 && n<2) {
                    miny3 = Math.abs(VCC[m][n].getCoordenada_y() - y1);
                    miny4 = Math.abs(GND[m][n].getCoordenada_y() - y1);
                    minx3 = Math.abs(VCC[m][n].getCoordenada_x() - x1);
                    if(minx3 < minx4){
                        minx4= minx3;
                      //  Toast.makeText(getApplicationContext(), "m =   " + m1, Toast.LENGTH_SHORT).show();
                        l1 = m;
                        //Toast.makeText(getApplicationContext(), "l=" + l1, Toast.LENGTH_SHORT).show();
                    }
                }
                if (minx1 < minx) {
                    minx = minx1;
                    j = m;
                }
                if (miny1 < miny) {
                    miny = miny1;
                    i = n;
                    k = 1;
                }
                if (miny2 < miny) {
                    miny = miny2;
                    i = n;
                    k = 2;
                }
                if (miny3 < miny){
                    miny = miny3;
                    i = n;
                    k = 3;
                }
                if (miny4 < miny){
                    miny = miny4;
                    i = n;
                    k = 4;
                }
            }
        }
        switch (k){
            case 1:
                minx = ABCDE[j][i].getCoordenada_x();
                miny = ABCDE[j][i].getCoordenada_y();
                break;
            case 2:
                minx = FGHIJ[j][i].getCoordenada_x();
                miny = FGHIJ[j][i].getCoordenada_y();
                break;
            case 3:
                miny = VCC[l1][i].getCoordenada_y();
                minx = VCC[l1][i].getCoordenada_x();
                break;
            case 4:
                miny = GND[l1][i].getCoordenada_y();
                minx = GND[l1][i].getCoordenada_x();
                break;
            default:
                break;
        }

    }

}
