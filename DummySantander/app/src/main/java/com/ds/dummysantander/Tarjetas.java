package com.ds.dummysantander;

public class Tarjetas {
    private String numTarjeta;
    private int numCuenta;
    private double saldo;
    private String tipo;
    private String limiteCredito;

    public Tarjetas(String numTarjeta, int numCuenta, double saldo, String tipo, String limiteCredito) {
        super();
        this.numTarjeta = numTarjeta;
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.tipo = tipo;
        this.limiteCredito = limiteCredito;

    }

    public Tarjetas()
    {}

    @Override
    public String toString(){
        return this.numCuenta + "\n" + this.tipo;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }
    public void setNumTarjeta(String  numTarjeta) {
        this.numCuenta = numCuenta;
    }

    public int getNumCuenta() { return numCuenta; }
    public void setNumCuenta(int numCuenta) { this.numCuenta = numCuenta; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLimiteCredito() {
        return limiteCredito;
    }
    public void setLimiteCredito(String limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

}
