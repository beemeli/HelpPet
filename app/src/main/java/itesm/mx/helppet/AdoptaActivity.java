package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class AdoptaActivity extends AppCompatActivity {
    private Spinner spinnerTamano, spinnerEdad, spinnerCategoria;
    private String mascota= "perro";
    private ImageButton catbutton;
    private ImageButton dogbutton;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopta);
        loadElements();
        user = getIntent().getStringExtra("user");

    }
    public void loadElements(){
        spinnerTamano = (Spinner)findViewById(R.id.spinner_tamano);
        ArrayAdapter<CharSequence> adapterTamano = ArrayAdapter.createFromResource(this,
                R.array.adopta_tamano_array, android.R.layout.simple_spinner_item);
        adapterTamano.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamano.setAdapter(adapterTamano);

        spinnerEdad = (Spinner)findViewById(R.id.spinner_edad);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this,
                R.array.adopta_edad_array, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapterEdad);

        spinnerCategoria = (Spinner)findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.adopta_categoria_array, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adapterCategoria);

        catbutton = (ImageButton)findViewById(R.id.catb);
        dogbutton = (ImageButton)findViewById(R.id.dogb);

    }


    public void buscar(View view) {
        String tamano = spinnerTamano.getSelectedItem().toString();
        String edad = spinnerEdad.getSelectedItem().toString();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        Mascota p = new Mascota();
        p.setTamano(tamano);
        p.setEdad(edad);
        p.setCategoria(categoria);
        p.setTipo(mascota);
        System.out.println(p);
        Intent it= new Intent(this, CentrosActivity.class);
        it.putExtra("mascota", p);
        it.putExtra("user", user);
        //p = (Mascota)getIntent().getSerializableExtra("mascota");

        startActivity(it);

    }
    public void dogbtn(View view) {
        mascota="perro";
        dogbutton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.dogb));
        catbutton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.cata));


    }
    public void catbtn(View view) {
        mascota= "gato";
        catbutton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.catb));
        dogbutton.setImageDrawable(getBaseContext().getResources().getDrawable(R.drawable.doga));


    }
}
