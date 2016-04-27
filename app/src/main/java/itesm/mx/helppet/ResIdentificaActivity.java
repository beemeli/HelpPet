package itesm.mx.helppet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResIdentificaActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_identifica);
        String user = getIntent().getStringExtra("user");

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        text = (TextView) findViewById(R.id.categoria);
        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);

        text1.setMovementMethod(new ScrollingMovementMethod());
        text2.setMovementMethod(new ScrollingMovementMethod());
        text3.setMovementMethod(new ScrollingMovementMethod());

        String res = getIntent().getStringExtra("res");
        System.out.println("Res2: " + res);
        if(res.equals("Energía Alta")){
            text.setText("Energìa Alta");
            img1.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/dinamico", null, null));
            img2.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/fiestero", null, null));
            img3.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/independiente", null, null));
            text2.setText("Pienso que todo es divertido, interesante y hecho para jugar, especialmente tú. Todo lo que hagas, yo también lo querré hacer.");
            text1.setText("¿Quieres hacer más ejercicio? Acción es mi segundo nombre. Mi estilo de vida te mantendrá motivado a salir de la casa y moverte.");
            text3.setText("Inteligente, independiente, ingenioso y de confianza, prefiero tomar mis propias decisiones pero te escucharé si tienes buenos argumentos.");
        }else{
            if(res.equals("Energía Media")){
                text.setText("Energía Media");
                img1.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/divertido", null, null));
                img2.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/jugueton", null, null));
                img3.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/timido", null, null));
                text1.setText("Soy de esos perros divertidos y amorosos, que están felices todo el tiempo y ven el vaso medio lleno. Busco alguien que ame reír y jugar. ");
                text2.setText("Soy un perro naturalmente juguetón, curioso y de confianza. Llévame a una caminata larga todos los días, dame algo que hacer.");
                text3.setText("Perro tímido y encantador busca dueño paciente con un estilo de vida relajado. Busco alguien que me ayude a salir de mi cascarón de manera gentil.");
            }else{
                text.setText("Energía Baja");
                img1.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/faldero", null, null));
                img2.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/inteligente", null, null));
                img3.setImageResource(getResources().getIdentifier("itesm.mx.helppet:drawable/tapete", null, null));
                text1.setText("¿Buscas una relación emocionalmente segura, mutuamente satisfactoria y de poco mantenimiento? Yo soy lo que necesitas. ");
                text3.setText("¿Te gusta la vida fácil? Entonces yo soy el compañero ideal para ti. Soy de esos perros relajados y despreocupados, que disfruta de largas siestas yver películas.");
                text2.setText("Yo tengo todo lo que buscas: soy inteligente y peludo, tengo 4 patas, amo aprender y vivo para complacer. Adelante, enséñame lo que quieras.");
            }
        }

    }
}
