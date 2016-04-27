package itesm.mx.helppet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TipoActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private ImageView imagen;
    private TipoMascota tipoMascota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);
        View view = findViewById(R.id.viewroot);

        //Intent
        tipoMascota = (TipoMascota)getIntent().getSerializableExtra("tipo");

        int id = getResources().getIdentifier("itesm.mx.helppet:drawable/" + tipoMascota.getImagen(), null, null);
        ImageView img = (ImageView)findViewById(R.id.tipo_imagen);
        img.setImageResource(id);

        TextView nombre = (TextView)findViewById(R.id.nombre);
        nombre.setText(tipoMascota.getNombre());

        TextView categoria = (TextView)findViewById(R.id.descripcion);
        categoria.setText("Cate: " + tipoMascota.getDescripcion());

        if(tipoMascota.getClasificacion().equals("Energía Alta")){
            view.setBackgroundColor(Color.parseColor("#83d982"));
        }else{
            if(tipoMascota.getClasificacion().equals("Energía Media")){
                view.setBackgroundColor(Color.parseColor("#f2794f"));
            }else{
                view.setBackgroundColor(Color.parseColor("#9b5ba4"));
            }
        }
    }
}
