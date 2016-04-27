package itesm.mx.helppet;



import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuestrosPerritosFragment extends ListFragment {

    private TiposAdapter tiposAdapter;
    private TipoMascota tipoMascota;
    private static ArrayList<TipoMascota> tipos ;


    public NuestrosPerritosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        tipos = null;
        tipos = new ArrayList<TipoMascota>();
        tiposAdapter = new TiposAdapter(getActivity(), cargaDatos());
        setListAdapter(tiposAdapter);

        super.onActivityCreated(savedInstanceState);
    }

    public ArrayList<TipoMascota> cargaDatos() {
        ArrayList<Centro> series = new ArrayList<Centro>();
        AssetManager assetManager = getActivity().getAssets();
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

        final TipoMascota tipo_selec = tiposAdapter.getItem(position);
        Intent it= new Intent(getActivity(), TipoActivity.class);
        it.putExtra("tipo", tipo_selec);
        //it.putExtra("user", user);
        //p = (Mascota)getIntent().getSerializableExtra("mascota");
        startActivity(it);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuestros_perritos, container, false);
    }

}
