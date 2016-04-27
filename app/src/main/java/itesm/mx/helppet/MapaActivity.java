package itesm.mx.helppet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity  implements OnMapReadyCallback{

    private GoogleMap googleMap;
    private Centro centro;
    private TextView nombre;
    private TextView direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        centro = (Centro)getIntent().getSerializableExtra("centro");
        nombre = (TextView) findViewById(R.id.nombreDir);
        direccion = (TextView) findViewById(R.id.direccionDir);
        System.out.println("***********Nombre: "+centro.getNombre());
        System.out.println("***********Dir: "+centro.getDireccion());
        nombre.setText(centro.getNombre());
        direccion.setText(centro.getDireccion());
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fragment3);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(centro.getLatitud(), centro.getLongitud());
        this.googleMap.addMarker(new MarkerOptions()
                .position(latLng).title(centro.getNombre()));
                //.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        ;
        // this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

        //CargaPoisAsyncTask cargaPoisAsyncTask = new CargaPoisAsyncTask(this, this.googleMap);
        //cargaPoisAsyncTask.execute(item.getArchivo(), item.getColor());
    }
}
