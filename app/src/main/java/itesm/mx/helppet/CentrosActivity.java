package itesm.mx.helppet;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CentrosActivity extends ListActivity {
    private CentrosAdapter centrosAdapter;
    private Centro centro;
    private static ArrayList<Centro> centros ;
    private Mascota mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centros);
        centros = null;
        mascota = (Mascota)getIntent().getSerializableExtra("mascota");
        System.out.println("**********"+mascota.getTipo());
        System.out.println("**********" + mascota.getTamano());
        System.out.println("**********"+mascota.getCategoria());

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
                if( jsonObjectDos.getString("tipo").equals(mascota.getTipo()) && jsonObjectDos.getString("categoria").equals(mascota.getCategoria()) && jsonObjectDos.getString("tamanio").equals(mascota.getTamano())) {
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

}
