package itesm.mx.helppet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


public class SignInSubActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText user;
    private EditText contra1;
    private EditText contra2,telefono, nombre;
    private HelpPetDAO mydb;
    private GoogleMap googleMap;
    private boolean flag;
    private LatLng direccion;
    private DBHelper helper=null;


    // private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sub);
        user = (EditText)findViewById(R.id.nombreuser);
        contra1 = (EditText)findViewById(R.id.contraS);
        contra2 = (EditText)findViewById(R.id.contraS2);
        telefono = (EditText)findViewById(R.id.telefono);
        nombre = (EditText)findViewById(R.id.nombreuser);

        mydb = new HelpPetDAO(this);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        // mydb = new DBHelper(this);

        helper = getHelper(this, DBHelper.class);

    }


    public void registrate(View view) {

        if(user.getText().toString().length()>0
                && contra1.getText().toString().length()>0
                && contra2.getText().toString().length()>0
                && direccion!=null
                && telefono.getText().toString().length()>0
                && nombre.getText().toString().length()>0
                ){

           // if(user.getText().toString().contains("@")){
                if(contra1.getText().toString().equals(contra2.getText().toString())){


                    try {

                        Double lat = direccion.latitude;
                        Double lon = direccion.longitude;


                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(this, Locale.getDefault());

                            addresses = geocoder.getFromLocation(lat, lon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName();



                        Dao daoUsuario = getHelper(this, DBHelper.class).getUsuarioDao();

                        Usuarios u = new Usuarios();
                        u.setId(user.getText().toString());
                        u.setPassword(contra1.getText().toString());
                        u.setNombre(nombre.getText().toString());
                        u.setTelefono(telefono.getText().toString());
                        u.setLatitud(lat);
                        u.setLongitud(lon);
                        u.setDireccion(address+","+city+", "+state+", "+country);

                        daoUsuario.create(u);

                        Intent it= new Intent(this, NavigationActivity.class);
                        Toast.makeText(this, R.string.bienvenido, Toast.LENGTH_LONG).show();

                        it.putExtra("user",user.getText().toString());
                        startActivity(it);
                        finish();

                    }catch(Exception e ){
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
    private DBHelper getHelper(SignInSubActivity SignInSubActivity, Class<DBHelper> dbHelperClass) {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, DBHelper.class);
        }
        return helper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

}
