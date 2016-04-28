package itesm.mx.helppet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaLugarActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_lugar);

        tipo  = getIntent().getStringExtra("lugar");
        System.out.println("el tipo es: "+tipo);

        MapFragment mapFragment = (MapFragment)getFragmentManager()
                .findFragmentById(R.id.fragment_mapa);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng latLng = new LatLng(19.282026, -99.135494);

        this.googleMap.addMarker(new MarkerOptions()
                .position(latLng).title("this is NOT Spartaaaa"))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

        ;
        // this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

        CargaLugaresAsyncTask cargaPoisAsyncTask = new CargaLugaresAsyncTask(this, this.googleMap);
        cargaPoisAsyncTask.execute(tipo, "AZURE");

    }

}
