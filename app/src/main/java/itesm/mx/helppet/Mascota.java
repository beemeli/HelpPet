package itesm.mx.helppet;

import java.io.Serializable;

/**
 * Created by beeme on 06/03/2016.
 */
public class Mascota implements Serializable {
    private String nombre;
    private String tipo;
    private String edad;
    private String categoria;
    private String tamano;

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", edad='" + edad + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tamano='" + tamano + '\'' +
                '}';
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
