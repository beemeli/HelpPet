package itesm.mx.helppet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;


public class AdoptaFragment extends Fragment {
    private Spinner spinnerTamano, spinnerEdad, spinnerCategoria;
    private String mascota= "perro";
    private ImageButton catbutton;
    private ImageButton dogbutton;
    private String user;
    private Button buscar;

    public AdoptaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        loadElements();
        user = getActivity().getIntent().getStringExtra("user");
        super.onActivityCreated(savedInstanceState);

    }

    public void loadElements(){
        spinnerTamano = (Spinner)getView().findViewById(R.id.spinner_tamano);
        ArrayAdapter<CharSequence> adapterTamano = ArrayAdapter.createFromResource(getActivity(),
                R.array.adopta_tamano_array, android.R.layout.simple_spinner_item);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);

        spinnerEdad = (Spinner)getView().findViewById(R.id.spinner_edad);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(getActivity(),
                R.array.adopta_edad_array, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerCategoria = (Spinner)getView().findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(getActivity(),
                R.array.adopta_categoria_array, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        catbutton = (ImageButton)getView().findViewById(R.id.catb);
        dogbutton = (ImageButton)getView().findViewById(R.id.dogb);
        buscar = (Button)getView().findViewById(R.id.buscar);

        catbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota= "gato";
                catbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.catb));
                dogbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.doga));

            }
        });
        dogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota="perro";
                dogbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.dogb));
                catbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.cata));

            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tamano = spinnerTamano.getSelectedItem().toString();
                String edad = spinnerEdad.getSelectedItem().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();

                Mascota p = new Mascota();
                p.setTamanio(tamano);
                p.setEdad(edad);
                p.setCategoria(categoria);
                p.setTipo(mascota);
                System.out.println(p);
                Intent it= new Intent(getActivity(), CentrosActivity.class);
                it.putExtra("mascota", p);
                it.putExtra("user", user);
                //p = (Mascota)getIntent().getSerializableExtra("mascota");

                startActivity(it);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adopta, container, false);
    }


}
