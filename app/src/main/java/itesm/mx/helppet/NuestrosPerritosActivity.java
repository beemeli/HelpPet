package itesm.mx.helppet;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NuestrosPerritosActivity extends ListActivity {

    private TiposAdapter tiposAdapter;
    private TipoMascota tipoMascota;
    private static ArrayList<TipoMascota> tipos ;
    Animation animTranslate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nuestros_perritos);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        tipos = null;
        tipos = new ArrayList<TipoMascota>();
        tiposAdapter = new TiposAdapter(this, cargaDatos());
        setListAdapter(tiposAdapter);
    }

    public ArrayList<TipoMascota> cargaDatos() {
        ArrayList<Centro> series = new ArrayList<Centro>();
        AssetManager assetManager = this.getAssets();
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open("tipos.json"));
            bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.ready()) {
                stringBuffer.append(bufferedReader.readLine());
            }
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            //JSONObject seriesjson = jsonObject.getJSONObject("series");
            JSONArray jsonArray = jsonObject.getJSONArray("tipos");

            //INTENT
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectDos = jsonArray.getJSONObject(i);
                TipoMascota tipo = new TipoMascota(jsonObjectDos.getString("nombre"), jsonObjectDos.getString("descripcion"), jsonObjectDos.getString("clasificacion"), jsonObjectDos.getString("imagen"));
                tipos.add(tipo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tipos;
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        final TipoMascota tipo_selec = tiposAdapter.getItem(position);
        Intent it= new Intent(this, TipoActivity.class);
        it.putExtra("tipo", tipo_selec);
        //it.putExtra("user", user);
        //p = (Mascota)getIntent().getSerializableExtra("mascota");
        startActivity(it);

    }
}
