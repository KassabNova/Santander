package com.ds.dummysantander;

public class Cliente {
    private int id;
    private int cuenta;
    private String password;
    private String fechaCreacion;
    private String ultimaConexion;

    public Cliente(int id, int cuenta, String password, String fechaCreacion, String ultimaConexion) {
        super();
        this.id = id;
        this.cuenta = cuenta;
        this.password = password;
        this.fechaCreacion = fechaCreacion;
        this.ultimaConexion = ultimaConexion;

    }

    public Cliente()
    {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCuenta() {
        return cuenta;
    }
    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUltimaConexion() {
        return ultimaConexion;
    }
    public void setUltimaConexion(String ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

}
