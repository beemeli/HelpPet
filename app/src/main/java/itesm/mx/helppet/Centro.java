package itesm.mx.helppet;

import java.io.Serializable;

/**
 * Created by Leslie on 13/03/2016.
 */
public class Centro implements Serializable {
    private String nombre;
    private String tipo;
    //private String edad;
    private String categoria;
    private String tamanio;
    private String telefono;
    private String direccion;
    private double latitud;
    private double longitud;

    public Centro(String nombre, String tipo, String categoria, String tamanio, String telefono, String direccion, double latitud, double longitud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.tamanio = tamanio;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamano(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
