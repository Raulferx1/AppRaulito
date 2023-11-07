package com.example.appraulito;
public class Peces {
    int id;
    String nombre;
    String especie;
    String longitud;
    String peso;
    String hora_captura;

    public Peces() {
    }

    public Peces(int id, String nombre, String especie, String longitud, String peso, String hora_captura) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.longitud = longitud;
        this.peso = peso;
        this.hora_captura = hora_captura;
    }

    public Peces(String nombre, String especie, String longitud, String peso, String hora_captura) {
        this.nombre = nombre;
        this.especie = especie;
        this.longitud = longitud;
        this.peso = peso;
        this.hora_captura = hora_captura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getHoraCaptura() {
        return hora_captura;
    }

    public void setHoraCaptura(String hora_captura) {
        this.hora_captura = hora_captura;
    }
}   