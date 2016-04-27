package itesm.mx.helppet;

import java.io.Serializable;

/**
 * Created by Leslie on 03/04/2016.
 */
public class TipoMascota implements Serializable {
    private String nombre;
    private String descripcion;
    private String clasificacion;
    private String imagen;

    public TipoMascota(String nombre, String descripcion, String clasificacion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
