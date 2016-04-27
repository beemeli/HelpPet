package itesm.mx.helppet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AprendeArticulosActivity extends AppCompatActivity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprende_articulos);
        user = getIntent().getStringExtra("user");
/*


        ListAprendeArticuloFragment nf = (ListAprendeArticuloFragment)
                getFragmentManager().findFragmentById(R.id.fragment_list_aprende_articulo);

        Bundle bundle = new Bundle();
        bundle.putString("opcion", "viviendo");
        nf.setArguments(bundle);*/

    }
}
