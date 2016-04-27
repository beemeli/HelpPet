package itesm.mx.helppet;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AprendeArticuloFragment extends Fragment {
    private TextView nombre;
    private TextView descripcion;
    private String user;
    private ImageView img;
    private String sitio="";
    public AprendeArticuloFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadElements();

        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {//https://www.healthcare.gov/"
                if(!sitio.equals("")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sitio)));
                }

            }
        } );


    }
    public void loadElements(){
        nombre= (TextView)getView().findViewById(R.id.nombre_articulo);
        nombre.setText("");
        descripcion= (TextView)getView().findViewById(R.id.descripcion_articulo);
        descripcion.setText("");
        img = (ImageView)getView().findViewById(R.id.imageArticulo);
    }
    public void setNombre(String title){

        this.nombre.setText(title);
    }

    public void setDescription(String title){

        this.descripcion.setText(title);
    }
    public void setSitio(String sitio){
        this.sitio = sitio;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_aprende_articulo, container, false);
    }



}
