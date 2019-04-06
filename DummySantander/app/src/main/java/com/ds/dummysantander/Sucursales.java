package com.ds.dummysantander;

public class Sucursales {
    private int idSucursal;
    private String direccion;
    private String tipo;
    private String nombre;
    private String longitud;
    private String latitud;

    public Sucursales(int idSucursal, String direccion, String tipo, String nombre, String longitud, String latitud) {
        super();
        this.idSucursal = idSucursal;
        this.direccion = direccion;
        this.tipo = tipo;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;

    }

    public Sucursales()
    {}

    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setPassword(String password) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

}
