package itesm.mx.helppet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by beeme on 05/03/2016.
 */
public class HelpPetSQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HelpPet.db";

    public static final String USUARIOS_TABLE_NAME = "usuarios";
    public static final String USUARIOS_COLUMN_ID = "id_usuario";
    public static final String USUARIOS_COLUMN_password = "password";
    public static final String[] USUARIOS_COLUMNAS = {USUARIOS_COLUMN_ID, USUARIOS_COLUMN_password};

    private static final int VERSION= 1;

    private static final  String USUARIO_CREATE =
            "create table " +
            USUARIOS_TABLE_NAME + "(" + USUARIOS_COLUMN_ID + " text primary key ,  " +
            USUARIOS_COLUMN_password + " text not null" +
                    "); ";




    public HelpPetSQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(USUARIO_CREATE);
        System.out.println("yup");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(  "DROP table IF Exists " +  USUARIOS_TABLE_NAME    );
        onCreate(db);
    }
}

