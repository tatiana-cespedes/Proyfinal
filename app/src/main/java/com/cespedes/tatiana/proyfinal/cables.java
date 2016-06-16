package com.cespedes.tatiana.proyfinal;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by Tatiana on 13/06/2016.
 */
public class cables {

    matrices p1, p2;
  //  ArrayList<matrices> recorrido;
    int numero;

    public matrices getP1() {
        return p1;
    }

    public void setP1(matrices p1) {
        this.p1 = p1;
    }

    public matrices getP2() {
        return p2;
    }

    public void setP2(matrices p2) {
        this.p2 = p2;
    }

    public int getNumero() {
        return numero;
    }

    /*public ArrayList<matrices> getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(ArrayList<matrices> recorrido) {
        this.recorrido = recorrido;
    }
*/
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
