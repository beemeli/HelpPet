package itesm.mx.helppet;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Leslie on 29/04/2016.
 */
@DatabaseTable
public class Usuarios {
    public static final String ID_USUARIO = "id_usuario";
    public static final String PASSWORD ="password";
    public static final String NOMBRE ="nombre";
    public static final String TELEFONO ="telefono";
    public static final String DIRECCION ="direccion";
    public static final String LATITUD ="latitud";
    public static final String LONGITUD = "longitud";

    @DatabaseField(id=true, columnName = ID_USUARIO)
    private String id;
    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;
    @DatabaseField(columnName = NOMBRE)
    private String nombre;
    @DatabaseField(columnName = TELEFONO)
    private String telefono;
    @DatabaseField(columnName = DIRECCION)
    private String direccion;
    @DatabaseField(columnName = LATITUD)
    private double latitud;
    @DatabaseField(columnName = LONGITUD)
    private double longitud;

    public Usuarios(String id, String password, String nombre, String telefono, String direccion, double latitud, double longitud) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Usuarios(){

    }

    public static String getIdUsuario() {
        return ID_USUARIO;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getNOMBRE() {
        return NOMBRE;
    }

    public static String getTELEFONO() {
        return TELEFONO;
    }

    public static String getDIRECCION() {
        return DIRECCION;
    }

    public static String getLATITUD() {
        return LATITUD;
    }

    public static String getLONGITUD() {
        return LONGITUD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
