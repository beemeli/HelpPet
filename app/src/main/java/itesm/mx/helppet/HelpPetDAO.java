package itesm.mx.helppet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beeme on 05/03/2016.
 */
public class HelpPetDAO {


    private SQLiteDatabase database;
    private HelpPetSQLiteHelper dbHelper;

    public HelpPetDAO(Context context){

        dbHelper = new HelpPetSQLiteHelper(context);
    }
    public void dropUsuario(){
        dbHelper.onUpgrade(database, 1,2);
    }
    public long insertUsuario (Usuario user){
        System.out.println("---" + user.toString());
    //    SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HelpPetSQLiteHelper.USUARIOS_COLUMN_ID, user.getId());
        values.put(HelpPetSQLiteHelper.USUARIOS_COLUMN_password, user.getPassword());


        System.out.println(HelpPetSQLiteHelper.USUARIOS_TABLE_NAME);
        System.out.println(values);
        long resultado = database.insert(HelpPetSQLiteHelper.USUARIOS_TABLE_NAME, null, values);

        return resultado;

    }




 /*   public void updateHelpPetUnit(Unit unit){

        ContentValues contentValues = new ContentValues();
        contentValues.put(HelpPetSQLiteHelper.COLUMNA_SUMMARY, unit.getSummary());

        database.update(
                HelpPetSQLiteHelper.HelpPet_TABLA,
                contentValues,
                HelpPetSQLiteHelper.COLUMNA_ID + "=" + unit.getId(),
                null);


    }

    public void deleteHelpPetUnit(Unit unit) {

        database.delete(HelpPetSQLiteHelper.HelpPet_TABLA,
                HelpPetSQLiteHelper.COLUMNA_ID + "=" + unit.getId(), null);



    }
*/


    public Usuario getUsuario(String user){
        Cursor res =  database.rawQuery
                ( "select * from "+ HelpPetSQLiteHelper.USUARIOS_TABLE_NAME +" " +
                        "where "+HelpPetSQLiteHelper.USUARIOS_COLUMN_ID+"=?", new String [] {user});

        Usuario u= null;
        if (res.moveToFirst()){
            u= new Usuario(
                    res.getString(res.getColumnIndex(HelpPetSQLiteHelper.USUARIOS_COLUMN_ID)),
                    res.getString(res.getColumnIndex(HelpPetSQLiteHelper.USUARIOS_COLUMN_password))
            );

        }
        else{
            System.out.println("ERRRROR");
        }

        return u;
    }



    public void open() {

        database = dbHelper.getReadableDatabase();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public void close(){
        database.close();

    }

}
