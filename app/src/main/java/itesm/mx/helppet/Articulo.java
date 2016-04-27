package itesm.mx.helppet;

/**
 * Created by beeme on 07/03/2016.
 */
public class Articulo {
    private String nombre;
    private String descripcion;
    private String sitio;

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

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
