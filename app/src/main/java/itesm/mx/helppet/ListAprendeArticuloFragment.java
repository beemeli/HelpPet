package itesm.mx.helppet;


import android.app.ListFragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListAprendeArticuloFragment  extends ListFragment {
    private ArrayAdapter<String> datos;
    private ArrayAdapter<String> desc;
    private ArrayAdapter<Articulo> items;
    private ArrayAdapter<Articulo> itemDesc;


    private String opcion;


    public ListAprendeArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        opcion = getActivity().getIntent().getStringExtra("opcion");
        System.out.println("---------------------" + opcion);
        ImageView im = (ImageView)getActivity().findViewById(R.id.imageArticulo);
        switch (opcion){
            case "antes":
                im.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.article));

                break;
            case "cuidados":
                im.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.circleb));

                break;

            case "viviendo":
                im.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.circleg));

                break;



        }


        items = new ArrayAdapter<Articulo>(getActivity(),
                android.R.layout.simple_list_item_1,
                getAriculos());

        setListAdapter(items);



    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        itemDesc = new ArrayAdapter<Articulo>(getActivity(),
                android.R.layout.simple_list_item_1,
                getAriculos());
        setListAdapter(itemDesc);

        Articulo aux = itemDesc.getItem(position);


        AprendeArticuloFragment nf=(AprendeArticuloFragment)
                getFragmentManager().findFragmentById(R.id.fragment_aprende_articulo);




        if(nf!=null){
            nf.setNombre(aux.getNombre());
            nf.setDescription(aux.getDescripcion());
            nf.setSitio(aux.getSitio());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_aprende_articulo, container, false);

    }

    public ArrayList<Articulo> getAriculos() {
        String archivo = opcion;
        AssetManager manager = getActivity().getAssets();
        BufferedReader lector = null;
        ArrayList<Articulo> resultado = new ArrayList<>();
        try{
            InputStreamReader input =  new InputStreamReader( manager.open(archivo + ".json") );

            lector = new BufferedReader(input);
            String data = "";
            while(lector.ready()){
                data += lector.readLine();
            }
            lector.close();
            //Toast.makeText(ct, data, Toast.LENGTH_LONG).show();
            Log.i("Data", data);
            resultado = procesaJSON(data);
        }catch(Exception ex ){
            ex.printStackTrace();
        }


        return resultado;
    }

    public ArrayList<Articulo> procesaJSON(String datos){
        ArrayList<Articulo> data = new ArrayList<>();
        try{
            JSONObject object = new JSONObject(datos);
            JSONArray coleccion = object.getJSONArray("pois");
            for (int i = 0; i < coleccion.length(); i++) {
                JSONObject ob = coleccion.getJSONObject(i);
                String nombre = ob.getString("nombre");
                String descripcion = ob.getString("descripcion");
                String sitio = ob.getString("sitio");

                Articulo a = new Articulo();
                a.setNombre(nombre);
                a.setDescripcion(descripcion);
                a.setSitio(sitio);

                data.add(a);


            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return data;

    }

}
