package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class IdentificaActivity extends AppCompatActivity {
    private String hogar="casa";
    private String user;
    private Spinner spinnerTiempo, spinnerEntrenamiento, spinnerEjercicio;
    private ImageButton casabtn;
    private ImageButton departamentobtn;
    private ImageButton mansionbtn;
    private int puntaje = 0;
    private boolean bandera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifica);
        loadElements();
        user = getIntent().getStringExtra("user");
        puntaje = 0;
    }

    public void loadElements(){
        spinnerTiempo = (Spinner)findViewById(R.id.spinner_tiempo);
        ArrayAdapter<CharSequence> adapterTiempo = ArrayAdapter.createFromResource(this,
                R.array.spinner_tiempo_array, android.R.layout.simple_spinner_item);
        adapterTiempo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiempo.setAdapter(adapterTiempo);

        spinnerEntrenamiento = (Spinner)findViewById(R.id.spinner_entrenamiento);
        ArrayAdapter<CharSequence> adapterEntrenamiento = ArrayAdapter.createFromResource(this,
                R.array.spinner_entrenamiento_array, android.R.layout.simple_spinner_item);
        adapterEntrenamiento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrenamiento.setAdapter(adapterEntrenamiento);

        spinnerEjercicio = (Spinner)findViewById(R.id.spinner_ejercicio);
        ArrayAdapter<CharSequence> adapterEjercicio = ArrayAdapter.createFromResource(this,
                R.array.spinner_ejercicio_array, android.R.layout.simple_spinner_item);
        adapterEjercicio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEjercicio.setAdapter(adapterEjercicio);

        casabtn = (ImageButton)findViewById(R.id.homei);
        departamentobtn = (ImageButton)findViewById(R.id.departamentoi);
        mansionbtn = (ImageButton)findViewById(R.id.mansioni);

    }

    public void casabtn(View view) {
        hogar = "casa";
        casabtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.homeb));
        departamentobtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.apartmentg));
        mansionbtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.luxuryg));
        bandera = true;
    }

    public void mansionbtn(View view) {
        hogar = "mansion";

        casabtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.homeg));
        departamentobtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.apartmentg));
        mansionbtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.luxuryb));
        bandera = true;
    }

    public void departamentobtn(View view) {
        hogar = "departamento";

        casabtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.homeg));
        departamentobtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.apartmentb));
        mansionbtn.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.luxuryg));
        bandera = true;
    }

    public void buscar(View view) {
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

            Intent it = new Intent(this, ResIdentificaActivity.class);
            System.out.println("Res:" + res);
            it.putExtra("res", res);
            it.putExtra("user", user);
            System.out.println("Puntaje:" + puntaje);
            //p = (Mascota)getIntent().getSerializableExtra("mascota");
            startActivity(it);
        }else{
            Toast.makeText(this, R.string.llenarcampos, Toast.LENGTH_LONG).show();
        }

    }
}
