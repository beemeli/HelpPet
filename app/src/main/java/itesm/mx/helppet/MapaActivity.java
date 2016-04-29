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
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;
    private TextView nombreTV;
    private TextView direccionTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        nombre = getIntent().getStringExtra("nombre");
        direccion = getIntent().getStringExtra("direccion");
        latitud = getIntent().getDoubleExtra("latitud", 0.0);
        longitud = getIntent().getDoubleExtra("longitud", 0.0);
        nombreTV = (TextView) findViewById(R.id.nombreDir);
        direccionTV = (TextView) findViewById(R.id.direccionDir);
        System.out.println("***********Nombre: " + nombre);
        System.out.println("***********Dir: " + direccion);
        System.out.println("***********Lat: " + latitud);
        System.out.println("***********Lon: " + longitud);
        nombreTV.setText(nombre);
        direccionTV.setText(direccion);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fragment3);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(latitud, longitud);
        this.googleMap.addMarker(new MarkerOptions()
                .position(latLng).title(nombre));
                //.setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
        ;
        // this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

        //CargaPoisAsyncTask cargaPoisAsyncTask = new CargaPoisAsyncTask(this, this.googleMap);
        //cargaPoisAsyncTask.execute(item.getArchivo(), item.getColor());
    }
}
