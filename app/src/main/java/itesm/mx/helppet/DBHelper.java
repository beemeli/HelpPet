package itesm.mx.helppet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Leslie on 27/04/2016.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "helppet.db";
    private static final int DATABASE_VERSION = 2;

    /*private Dao<Centros, Integer> centroDao;
    private RuntimeExceptionDao<Centros, Integer> centroRuntimeDao = null;*/

    private Dao<Usuarios, Integer> usuarioDao;
    //private RuntimeExceptionDao<Centros, Integer> usuarioRuntimeDao = null;

    private Dao<Mascota, Integer> mascotaDao;
    //private RuntimeExceptionDao<Centros, Integer> mascotaRuntimeDao = null;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Usuarios.class);
            TableUtils.createTable(connectionSource, Mascota.class);
            //TableUtils.createTable(connectionSource, Centros.class);
            Usuarios u1 = new Usuarios("JuanR", "234342", "Juan Hernández", "7867889", "Paseo Acoxpa 211, Ciudad de Mexico", 19.285781, -99.1488464);
            Usuarios u2 = new Usuarios("PauE3", "234342", "Paulina Villaseñor", "5555534", "Insurgentes Sur 1211, Ciudad de Mexico",19.4167126,-99.1672754);
            Usuarios u3 = new Usuarios("LuisaF2", "11111", "Luisa Ayala", "55412354", "Divisiòn del Norte 1500, Ciudad de Mexico", 19.3067571,-99.1340096);
            //getUsuarioDao().create(new Usuarios("U1", "234342", "Leslie", "7867889", "direccion aosidha", -19.001, 19.212));
            getUsuarioDao().create(u1);
            getUsuarioDao().create(u2);
            getUsuarioDao().create(u3);

            Mascota m1 = new Mascota("Sam", "perro", "Juguetón", "Grande", "de 6 a 12 meses", u1);
            Mascota m2 = new Mascota("Petunia", "gato", "Juguetón", "Grande", "de 6 a 12 meses",u1);
            Mascota m3 = new Mascota("Coby", "perro", "Fiestero", "Grande", "de 6 a 12 meses", u1);
            Mascota m4 = new Mascota("Bobby", "gato", "Fiestero", "Grande", "de 6 a 12 meses",u1);
            Mascota m5 = new Mascota("Chester", "perro", "Independiente", "Grande", "de 6 a 12 meses", u1);
            Mascota m6 = new Mascota("Bambi", "gato", "Independiente", "Grande", "de 6 a 12 meses",u1);
            Mascota m7 = new Mascota("Kiara", "perro", "Dinámico", "Grande", "de 6 a 12 meses", u1);
            Mascota m8 = new Mascota("Bella", "gato", "Dinámico", "Grande", "de 6 a 12 meses",u1);
            Mascota m9 = new Mascota("Goku", "perro", "Divertido", "Grande", "de 6 a 12 meses", u1);
            Mascota m10 = new Mascota("Doki", "gato", "Divertido", "Grande", "de 6 a 12 meses",u1);
            Mascota m37 = new Mascota("Ami", "perro", "Divertido", "Grande", "de 6 a 12 meses", u2);
            Mascota m38 = new Mascota("Bree", "gato", "Divertido", "Grande", "de 6 a 12 meses",u2);
            Mascota m11 = new Mascota("Abba", "perro", "Tímido", "Grande", "de 6 a 12 meses", u1);
            Mascota m12 = new Mascota("Goofy", "gato", "Tímido", "Grande", "de 6 a 12 meses",u1);

            Mascota m13 = new Mascota("Ami", "perro", "Juguetón", "Mediano", "de 6 a 12 meses", u2);
            Mascota m14 = new Mascota("Petunia", "gato", "Juguetón", "Mediano", "de 6 a 12 meses",u2);
            Mascota m15 = new Mascota("Bree", "perro", "Fiestero", "Mediano", "de 6 a 12 meses", u2);
            Mascota m16 = new Mascota("Keko", "gato", "Fiestero", "Mediano", "de 6 a 12 meses",u2);
            Mascota m17 = new Mascota("Saba", "perro", "Independiente", "Mediano", "de 6 a 12 meses", u2);
            Mascota m18 = new Mascota("Wurst", "gato", "Independiente", "Mediano", "de 6 a 12 meses",u2);
            Mascota m19 = new Mascota("Hachi", "perro", "Dinámico", "Mediano", "de 6 a 12 meses", u2);
            Mascota m20 = new Mascota("Huno", "gato", "Dinámico", "Mediano", "de 6 a 12 meses",u2);
            Mascota m21 = new Mascota("Bolt", "perro", "Divertido", "Mediano", "de 6 a 12 meses", u2);
            Mascota m22 = new Mascota("Penny", "gato", "Divertido", "Mediano", "de 6 a 12 meses",u2);
            Mascota m23 = new Mascota("Nika", "perro", "Tímido", "Mediano", "de 6 a 12 meses", u2);
            Mascota m24 = new Mascota("Terry", "gato", "Tímido", "Mediano", "de 6 a 12 meses",u2);

            Mascota m25 = new Mascota("Cloe", "perro", "Juguetón", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m26 = new Mascota("Molly", "gato", "Juguetón", "Pequeño", "de 6 a 12 meses",u3);
            Mascota m27 = new Mascota("Figo", "perro", "Fiestero", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m28 = new Mascota("Dora", "gato", "Fiestero", "Pequeño", "de 6 a 12 meses",u3);
            Mascota m29 = new Mascota("Noa", "perro", "Independiente", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m30 = new Mascota("Bella", "gato", "Independiente", "Pequeño", "de 6 a 12 meses",u3);
            Mascota m31 = new Mascota("Bailey", "perro", "Dinámico", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m32 = new Mascota("Roxie", "gato", "Dinámico", "Pequeño", "de 6 a 12 meses",u3);
            Mascota m33 = new Mascota("Jax", "perro", "Divertido", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m34 = new Mascota("Sky", "gato", "Divertido", "Pequeño", "de 6 a 12 meses",u3);
            Mascota m35 = new Mascota("Raven", "perro", "Tímido", "Pequeño", "de 6 a 12 meses", u3);
            Mascota m36 = new Mascota("Blue", "gato", "Tímido", "Pequeño", "de 6 a 12 meses",u3);

            getMascotaDao().create(m1);
            getMascotaDao().create(m2);
            getMascotaDao().create(m3);
            getMascotaDao().create(m4);
            getMascotaDao().create(m5);
            getMascotaDao().create(m6);
            getMascotaDao().create(m7);
            getMascotaDao().create(m8);
            getMascotaDao().create(m9);
            getMascotaDao().create(m10);
            getMascotaDao().create(m11);
            getMascotaDao().create(m12);
            getMascotaDao().create(m13);
            getMascotaDao().create(m14);
            getMascotaDao().create(m15);
            getMascotaDao().create(m16);
            getMascotaDao().create(m17);
            getMascotaDao().create(m18);
            getMascotaDao().create(m19);
            getMascotaDao().create(m20);
            getMascotaDao().create(m21);
            getMascotaDao().create(m22);
            getMascotaDao().create(m23);
            getMascotaDao().create(m24);
            getMascotaDao().create(m25);
            getMascotaDao().create(m26);
            getMascotaDao().create(m27);
            getMascotaDao().create(m28);
            getMascotaDao().create(m29);
            getMascotaDao().create(m30);
            getMascotaDao().create(m31);
            getMascotaDao().create(m32);
            getMascotaDao().create(m33);
            getMascotaDao().create(m34);
            getMascotaDao().create(m35);
            getMascotaDao().create(m36);
            getMascotaDao().create(m37);
            getMascotaDao().create(m38);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database, connectionSource);
    }

    /*public Dao<Centros, Integer> getCentroDao() throws SQLException {
        if (centroDao == null) {
            centroDao = getDao(Centros.class);
        }
        return centroDao;
    }*/

    public Dao<Usuarios, Integer> getUsuarioDao() throws SQLException {
        if (usuarioDao == null) {
            usuarioDao = getDao(Usuarios.class);
        }
        return usuarioDao;
    }

    public Dao<Mascota, Integer> getMascotaDao() throws SQLException {
        if (mascotaDao == null) {
            mascotaDao = getDao(Mascota.class);
        }
        return mascotaDao;
    }

    @Override
    public void close() {
        super.close();
        //centroDao = null;
        mascotaDao = null;
        usuarioDao = null;
    }
}
