package itesm.mx.helppet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class AgregarMascotaFragment extends Fragment {
    private EditText nombreET;
    private Spinner spinnerTamano, spinnerEdad, spinnerCategoria;
    private String mascota= "perro";
    private ImageButton catbutton;
    private ImageButton dogbutton;
    private Button guardar;
    private String user;

    private DBHelper helper=null;

    public AgregarMascotaFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_mascota, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = getActivity().getIntent().getStringExtra("user");
        System.out.println("**User: " + user);
        loadElements();


    }



    public void loadElements(){
        spinnerTamano = (Spinner)getView().findViewById(R.id.tamaniospinner);
        ArrayAdapter<CharSequence> adapterTamano = ArrayAdapter.createFromResource(getContext(),
                R.array.adopta_tamano_array, android.R.layout.simple_spinner_item);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);

        spinnerEdad = (Spinner)getView().findViewById(R.id.edadspinner);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(getContext(),
                R.array.adopta_edad_array, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerCategoria = (Spinner)getView().findViewById(R.id.categoriaspinner);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(getContext(),
                R.array.adopta_categoria_array, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        catbutton = (ImageButton)getView().findViewById(R.id.catb);
        catbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota = "gato";
                catbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.catb));
                dogbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.doga));
            }
        });
        dogbutton = (ImageButton)getView().findViewById(R.id.dogb);
        dogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mascota="perro";
                dogbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.dogb));
                catbutton.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.cata));
            }
        });
        nombreET = (EditText)getView().findViewById(R.id.nombreEditText);

        guardar = (Button)getView().findViewById(R.id.registrar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tamano = spinnerTamano.getSelectedItem().toString();
                String edad = spinnerEdad.getSelectedItem().toString();
                String categoria = spinnerCategoria.getSelectedItem().toString();
                String nombre = nombreET.getText().toString(); //¿""?
                //DB
                helper = getHelper((NavigationActivity) getActivity(), DBHelper.class);
                try{
                    //Dao dao = getHelper().getCentroDao();
                    Dao daoUsuario = getHelper((NavigationActivity) getActivity(), DBHelper.class).getUsuarioDao();
                    Dao daoMascota = getHelper((NavigationActivity) getActivity(), DBHelper.class).getMascotaDao();

                    if(nombre != null){
                        List<Usuarios> mas = daoUsuario.queryForAll();
                        Usuarios usuario=null;
                        boolean flag=false;
                        for(Usuarios u: mas){
                            if(u.getId().equals(user)) {
                                usuario = u;
                                break;
                            }
                        }

                        //Usuarios u = (Usuarios) daoUsuario.queryForId(user);
                        System.out.println("***USER QUERY: " + usuario.getDireccion());
                        Mascota m = new Mascota(nombre, mascota, categoria, tamano, edad, usuario);
                        daoMascota.create(m);
                        Toast.makeText(getActivity(), R.string.agregomascota, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(), R.string.llenacampos, Toast.LENGTH_LONG).show();
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("No SE OBTUVIERON MASCOTAS Y USUARIOS");
                }
            }
        });

    }




    //Metodos DB
    private DBHelper getHelper(NavigationActivity navigationActivity, Class<DBHelper> dbHelperClass) {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
        }
        return helper;
    }
}
