package itesm.mx.helppet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class IdentificaFragment extends Fragment {

    private String hogar="casa";
    private String user;
    private Spinner spinnerTiempo, spinnerEntrenamiento, spinnerEjercicio;
    private ImageButton casabtn;
    private ImageButton departamentobtn;
    private ImageButton mansionbtn;
    private Button buscar;
    private int puntaje = 0;
    private boolean bandera = true;


    public IdentificaFragment() {
        // Required empty public constructor
    }
    public void loadElements(){
        spinnerTiempo = (Spinner)getView().findViewById(R.id.spinner_tiempo);
        ArrayAdapter<CharSequence> adapterTiempo = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_tiempo_array, android.R.layout.simple_spinner_item);
        adapterTiempo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiempo.setAdapter(adapterTiempo);

        spinnerEntrenamiento = (Spinner)getView().findViewById(R.id.spinner_entrenamiento);
        ArrayAdapter<CharSequence> adapterEntrenamiento = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_entrenamiento_array, android.R.layout.simple_spinner_item);
        adapterEntrenamiento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrenamiento.setAdapter(adapterEntrenamiento);

        spinnerEjercicio = (Spinner)getView().findViewById(R.id.spinner_ejercicio);
        ArrayAdapter<CharSequence> adapterEjercicio = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_ejercicio_array, android.R.layout.simple_spinner_item);
        adapterEjercicio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEjercicio.setAdapter(adapterEjercicio);

        casabtn = (ImageButton)getView().findViewById(R.id.homei);
        departamentobtn = (ImageButton)getView().findViewById(R.id.departamentoi);
        mansionbtn = (ImageButton)getView().findViewById(R.id.mansioni);
        buscar = (Button)getView().findViewById(R.id.buscar);

        casabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hogar = "casa";
                casabtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.homeb));
                departamentobtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.apartmentg));
                mansionbtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.luxuryg));
                bandera = true;
            }
        });
        departamentobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hogar = "departamento";

                casabtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.homeg));
                departamentobtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.apartmentb));
                mansionbtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.luxuryg));
                bandera = true;
            }
        });
        mansionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hogar = "mansion";

                casabtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.homeg));
                departamentobtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.apartmentg));
                mansionbtn.setImageDrawable(getActivity().getBaseContext().getResources().getDrawable(R.drawable.luxuryb));
                bandera = true;
            }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bandera) {
                    String tiempo = spinnerTiempo.getSelectedItem().toString();
                    String entrenamiento = spinnerEntrenamiento.getSelectedItem().toString();
                    String ejercicio = spinnerEjercicio.getSelectedItem().toString();
                    puntaje = 0;
                    //Puntaje tiempo
                    if (tiempo.equals("Todo el tiempo")) {
                        puntaje += 5;
                    } else {
                        if (tiempo.equals("Algún tiempo")) {
                            puntaje += 4;
                        } else {
                            puntaje += 3;
                        }
                    }
                    //Puntaje entrenamiento
                    if (entrenamiento.equals("Mucho entrenamiento")) {
                        puntaje += 5;
                    } else {
                        if (entrenamiento.equals("Poco entrenamiento")) {
                            puntaje += 4;
                        } else {
                            puntaje += 3;
                        }
                    }
                    //Puntaje ejercicio
                    if (ejercicio.equals("7 horas o más")) {
                        puntaje += 5;
                    } else {
                        if (ejercicio.equals("3 a 6 horas")) {
                            puntaje += 4;
                        } else {
                            puntaje += 3;
                        }
                    }
                    //Puntaje hogar
                    if (hogar.equals("mansion")) {
                        puntaje += 5;
                    } else {
                        if (hogar.equals("casa")) {
                            puntaje += 4;
                        } else {
                            puntaje += 3;
                        }
                    }
                    String res = "";
                    if ((puntaje <= 20) && (puntaje > 16)) {
                        res = "Energía Alta";
                    } else {
                        if ((puntaje <= 16) && (puntaje > 13)) {
                            res = "Energía Media";
                        } else {
                            res = "Energía Baja";
                        }
                    }

                    Intent it = new Intent(getActivity(), ResIdentificaActivity.class);
                    System.out.println("Res:" + res);
                    it.putExtra("res", res);
                    it.putExtra("user", user);
                    System.out.println("Puntaje:" + puntaje);
                    //p = (Mascota)getIntent().getSerializableExtra("mascota");
                    startActivity(it);
                }else{
                    Toast.makeText(getActivity(), R.string.llenarcampos, Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        loadElements();
        user = getActivity().getIntent().getStringExtra("user");
        puntaje = 0;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identifica, container, false);
    }

}
