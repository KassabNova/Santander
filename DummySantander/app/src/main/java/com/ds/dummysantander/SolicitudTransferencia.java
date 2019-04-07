package com.ds.dummysantander;

public class SolicitudTransferencia {
    private String TarjetaOrigen;
    private String TarjetaDestino;
    private double Monto;
    private boolean PagoTerceros;
    private String Detalle;

    public SolicitudTransferencia(String tarjetaOrigen, String tarjetaDestino, double monto, boolean pagoTerceros, String detalle) {
        super();
        this.TarjetaOrigen = tarjetaOrigen;
        this.TarjetaDestino = tarjetaDestino;
        this.Monto = monto;
        this.PagoTerceros = pagoTerceros;
        this.Detalle = detalle;
    }

    public SolicitudTransferencia()
    {}

}
