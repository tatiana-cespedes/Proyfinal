package com.cespedes.tatiana.proyfinal;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Tatiana on 13/06/2016.
 */
public class cables {

    matrices pi, pf;
    matrices[] recorrido1;
    int numero;

    public matrices getPi() {
        return pi;
    }

    public void setPi(matrices pi) {
        this.pi = pi;
    }

    public matrices getPf() {
        return pf;
    }

    public void setPf(matrices pf) {
        this.pf = pf;
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public matrices[] getRecorrido() {
        return recorrido1;
    }

    public void setRecorrido(matrices[] recorrido) {
        this.recorrido1 = recorrido;
    }


    public cables( ArrayList<matrices> lista) {
        matrices newmatriz = new matrices();
        newmatriz.setCoordenada_x(0);
        newmatriz.setCoordenada_y(0);
        int longitud= lista.size();
        this.pi = newmatriz;
        this.pf = newmatriz;
        this.recorrido1 = new matrices[longitud];
        this.numero = 0;

        for(int i=0; i<longitud; i++){
            recorrido1[i] = lista.get(i);

        }

    }

}
