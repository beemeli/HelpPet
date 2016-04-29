package itesm.mx.helppet;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.j256.ormlite.android.apptools.OpenHelperManager.getHelper;

public class CentrosActivity extends ListActivity {
    private CentrosAdapter centrosAdapter;
    private Centro centro;
    private static ArrayList<Centro> centros ;
    private Mascota mascota;

    //BASE DE DATOS
    private DBHelper helper=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros);
        centros = null;
        mascota = (Mascota)getIntent().getSerializableExtra("mascota");
        System.out.println("**********"+mascota.getTipo());
        System.out.println("**********" + mascota.getTamanio());
        System.out.println("**********"+mascota.getCategoria());

        //DB
        helper = getHelper(this, DBHelper.class);
        try{
            //Dao dao = getHelper().getCentroDao();
            Dao daoUsuario = getHelper(this, DBHelper.class).getUsuarioDao();
            Dao daoMascota = getHelper(this, DBHelper.class).getMascotaDao();
            //Centros centro = new Centros("+Kota Acoxpa", "perro", "Juguetón", "Grande", "de 6 a 12 meses", "5555", "Paseo Acoxpa 211, Ciudad de Mexico", 19.285781, -99.1488464);
            /*Usuarios u = new Usuarios("U1", "234342", "Leslie", "7867889", "direccion aosidha", -19.001, 19.212);
            daoUsuario.create(new Usuarios("U1", "234342", "Leslie", "7867889", "direccion aosidha", -19.001, 19.212));

            Mascota m = new Mascota("Sam", "perro", "Juguetón", "Grande", "de 6 a 12 meses", u);
            Mascota m2 = new Mascota("Cofy", "gato", "Juguetón", "Grande", "de 6 a 12 meses",u);
            daoMascota.create(m);
            daoMascota.create(m2);*/


            List<Mascota> mascotas = daoMascota.queryForAll();
            for(Mascota mascota:mascotas){
                System.out.println("****Usuario y Mascota: " + mascota.getUsuario().getId() + " " + mascota.getNombre());
            }

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("No SE AGREGARON A LA BASE");
        }

        centros = new ArrayList<Centro>();
        centrosAdapter = new CentrosAdapter(this, cargaDatos());
        setListAdapter(centrosAdapter);
    }

    public ArrayList<Centro> cargaDatos() {
        ArrayList<Centro> series = new ArrayList<Centro>();
        AssetManager assetManager = this.getAssets();
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("mascotas.json"));
            bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.ready()) {
                stringBuffer.append(bufferedReader.readLine());
            }
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            //JSONObject seriesjson = jsonObject.getJSONObject("series");
            JSONArray jsonArray = jsonObject.getJSONArray("centros");

            //INTENT
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectDos = jsonArray.getJSONObject(i);
                if( jsonObjectDos.getString("tipo").equals(mascota.getTipo()) && jsonObjectDos.getString("categoria").equals(mascota.getCategoria()) && jsonObjectDos.getString("tamanio").equals(mascota.getTamanio())) {
                    Centro centro = new Centro(jsonObjectDos.getString("nombre"), jsonObjectDos.getString("tipo"), jsonObjectDos.getString("categoria"), jsonObjectDos.getString("tamanio"), jsonObjectDos.getString("tel"), jsonObjectDos.getString("direccion"), jsonObjectDos.getDouble("latitude"), jsonObjectDos.getDouble("longitude"));
                    centros.add(centro);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return centros;
    }


    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        final Centro centro_selec = centrosAdapter.getItem(position);
        Intent it= new Intent(this, MapaActivity.class);
        it.putExtra("centro", centro_selec);
        //it.putExtra("user", user);
        //p = (Mascota)getIntent().getSerializableExtra("mascota");
        startActivity(it);

    }

    //Metodos DB
    private DBHelper getHelper(CentrosActivity centrosActivity, Class<DBHelper> dbHelperClass) {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return helper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

}
