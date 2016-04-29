package itesm.mx.helppet;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable
public class Mascota implements Serializable {
    public static final String ID = "id";
    public static final String NOMBRE ="nombre";
    public static final String TIPO ="tipo";
    public static final String CATEGORIA ="categoria";
    public static final String TAMANIO ="tamanio";
    public static final String EDAD ="edad";
    public static final String USUARIO ="usuario";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = NOMBRE)
    private String nombre; //centro o persona
    @DatabaseField(columnName = TIPO)
    private String tipo; //gato o perro
    @DatabaseField(columnName = CATEGORIA)
    private String categoria;
    @DatabaseField(columnName = TAMANIO)
    private String tamanio;
    @DatabaseField(columnName = EDAD)
    private String edad;
    @DatabaseField(foreign = true, columnName = USUARIO)
    private Usuarios usuario;

    public Mascota(String nombre, String tipo, String categoria, String tamanio, String edad, Usuarios usuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.tamanio = tamanio;
        this.edad = edad;
        this.usuario = usuario;
    }

    public Mascota(){

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

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", edad='" + edad + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tamano='" + tamanio + '\'' +
                '}';
    }
}
