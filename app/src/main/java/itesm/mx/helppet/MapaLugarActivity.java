package itesm.mx.helppet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaLugarActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_lugar);

        tipo = getIntent().getStringExtra("lugar");
        System.out.println("el tipo es: " + tipo);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_mapa);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;


        // Enabling MyLocation Layer of Google Map
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            LatLng myPosition = new LatLng(latitude, longitude);

            this.googleMap.addMarker(new MarkerOptions()
                    .position(myPosition).title("Tú estás aquí"));
            //.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_history_24dp));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));

        } else{

            LatLng myPosition = new LatLng(19.282026, -99.135494);

            this.googleMap.addMarker(new MarkerOptions()
                    .position(myPosition).title("Tú estás aquí"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_history_24dp));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));

        }



        // this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        CargaLugaresAsyncTask cargaPoisAsyncTask = new CargaLugaresAsyncTask(this, this.googleMap);
        cargaPoisAsyncTask.execute(tipo, "AZURE");

/*
        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                if(marker.getSnippet()!=null){

                    if(!marker.getSnippet().contains("http")) {
                        String numero = marker.getSnippet();
                        callIntent.setData(Uri.parse("tel:" + numero));
                        startActivity(callIntent);
                    }
                    else{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marker.getSnippet())));

                    }

                }



                return false;

            }
        });
*/

    }

}
