package itesm.mx.helppet;

/**
 * Created by beeme on 05/03/2016.
 */
public class Usuario {
    private String id;
    private String password;

    public Usuario(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
