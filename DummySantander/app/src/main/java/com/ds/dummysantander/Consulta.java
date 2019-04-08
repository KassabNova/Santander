package com.ds.dummysantander;

import java.time.*;
import java.util.Date;

public class Consulta {
    private int id;
    private double monto;
    private String idTarjeta;
    private String fecha;
    private int concepto;
    private String detalle;

    public Consulta(int id, double monto, String idTarjeta, String fecha, int concepto, String detalle){
        this.id = id;
        this.monto = monto;
        this.idTarjeta = idTarjeta;
        this.fecha = fecha;
        this.concepto = concepto;
        this.detalle = detalle;
    }

    public Consulta(){}

    public int getId() {
        return id;
    }

    public double getMonto() {return this.monto; }

    public String getIdTarjeta() {
        return this.idTarjeta;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getDetalle() {return this.detalle;}

}
