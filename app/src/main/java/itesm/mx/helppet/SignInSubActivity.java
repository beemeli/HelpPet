package itesm.mx.helppet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SignInSubActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText user;
    private EditText contra1;
    private EditText contra2,telefono;
    private HelpPetDAO mydb;
    private GoogleMap googleMap;
    private boolean flag;
    private LatLng direccion;


    // private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sub);
        user = (EditText)findViewById(R.id.nombredr);
        contra1 = (EditText)findViewById(R.id.contraS);
        contra2 = (EditText)findViewById(R.id.contraS2);
        telefono = (EditText)findViewById(R.id.telefono);
        mydb = new HelpPetDAO(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        // mydb = new DBHelper(this);


    }


    public void registrate(View view) {

        if(user.getText().toString().length()>0
                && contra1.getText().toString().length()>0
                && contra2.getText().toString().length()>0
                && direccion!=null
                && telefono.getText().length()>0
                ){

           // if(user.getText().toString().contains("@")){
                if(contra1.getText().toString().equals(contra2.getText().toString())){
                    System.out.println(user.getText().toString()+ " ************"+contra1.getText().toString());
                    mydb.open();
                    System.out.println(mydb +"***********///////////***");

                    Double lat = direccion.latitude;
                    Double lon = direccion.longitude;

                    if(mydb.insertUsuario(new Usuario(user.getText().toString(),contra1.getText().toString())) !=-1){

                        Toast.makeText(this, R.string.registrook, Toast.LENGTH_LONG).show();
                        Intent it = new Intent();
                        setResult(RESULT_OK, it);
                        finish();

                    }
                    else{
                        Toast.makeText(this, R.string.usuarioyaexiste, Toast.LENGTH_LONG).show();

                    }

                }
                else{
                    Toast.makeText(this, R.string.contradiferentes, Toast.LENGTH_LONG).show();

                }

          /*  }
            else{
                Toast.makeText(this, R.string.usuarioinvalido, Toast.LENGTH_LONG).show();

            }*/


        }
        else{
            Toast.makeText(this, R.string.llenartodosloscampos, Toast.LENGTH_LONG).show();

        }


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
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));

        } else{

            LatLng myPosition = new LatLng(19.282026, -99.135494);

            this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 12.0f));

        }

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
        this.googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (!flag) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title("Mi dirección")
                            .draggable(true));
                    flag = true;
                    direccion = latLng;
                }
            }
        });
    }

}
