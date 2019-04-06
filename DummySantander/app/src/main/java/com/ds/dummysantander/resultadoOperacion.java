package com.ds.dummysantander;

public class resultadoOperacion {
    private int tipo;
    private String detalle;

    public resultadoOperacion(int tipo, String detalle) {
        super();
        this.tipo = tipo;
        this.detalle = detalle;
    }

    public resultadoOperacion()
    {}

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
