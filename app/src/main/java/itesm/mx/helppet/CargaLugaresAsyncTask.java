package itesm.mx.helppet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**sys
 * Created by harryHaller on 29/02/16.
 */
public class CargaLugaresAsyncTask extends AsyncTask<String, Void, ArrayList<Lugar>> {

    private Context ct;
    private ProgressDialog dialogo;
    private GoogleMap googleMap;
    private String color;




    public float colorM(String color){

        switch (color){
            case "AZURE":
                return BitmapDescriptorFactory.HUE_AZURE;

            case "BLUE":
                return BitmapDescriptorFactory.HUE_BLUE;

            case "YELLOW":
                return BitmapDescriptorFactory.HUE_YELLOW;

            case "ROSE":
                return BitmapDescriptorFactory.HUE_ROSE;

            case "RED":
                return BitmapDescriptorFactory.HUE_RED;

            case "GREEN":
                return BitmapDescriptorFactory.HUE_GREEN;

            case "VIOLET":
                return BitmapDescriptorFactory.HUE_VIOLET;

            case "MAGENTA":
                return BitmapDescriptorFactory.HUE_MAGENTA;

        }
        return 0;
    }
    public CargaLugaresAsyncTask(Context c, GoogleMap googleMap){
            ct = c;
            dialogo = new ProgressDialog(ct);
        this.googleMap= googleMap;
    }

    @Override
    protected ArrayList<Lugar> doInBackground(String... params) {
            // TODO Auto-generated method stub
            String archivo = params[0];
            color = params[1];
            AssetManager manager = ct.getAssets();
            BufferedReader lector = null;
            ArrayList<Lugar> resultado = new ArrayList<Lugar>();
            try{
            InputStreamReader input =  new InputStreamReader( manager.open(params[0] + ".json") );

            lector = new BufferedReader(input);
            String data = "";
            while(lector.ready()){
                data += lector.readLine();
            }
            lector.close();
            //Toast.makeText(ct, data, Toast.LENGTH_LONG).show();
                Log.i("Data", data);
                resultado = procesaJSON(data);
            }catch(Exception ex ){
                ex.printStackTrace();
            }


            return resultado;
    }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            dialogo.setTitle("Procesando...");
            dialogo.setCancelable(false);
            dialogo.show();
        }

    @Override
    protected void onPostExecute(ArrayList<Lugar> result) {

        if(result.size() > 0 ){
            for (Lugar sitio: result){
                LatLng latLng = new LatLng(sitio.getLat(), sitio.getLon());
                this.googleMap.addMarker(new MarkerOptions()
                        .title(sitio.getNombre())
                        .snippet(sitio.getSitio())
                        .position(latLng))
                        .setIcon(BitmapDescriptorFactory.defaultMarker(colorM(color)));
            }
            this.googleMap.moveCamera(CameraUpdateFactory.zoomTo(12.0f));
        }
        if(dialogo != null && dialogo.isShowing()){
            dialogo.dismiss();
        }
    }


    public ArrayList<Lugar> procesaJSON(String datos){
            ArrayList<Lugar> data = new ArrayList<>();
            try{
                JSONObject object = new JSONObject(datos);
                JSONArray coleccion = object.getJSONArray("lugares");
                for (int i = 0; i < coleccion.length(); i++) {
                    JSONObject ob = coleccion.getJSONObject(i);

                    String nombre = ob.getString("nombre");
                    String telefono = ob.getString("telefono");
                    String sitioW = ob.getString("sitio");
                    double latitude = ob.getDouble("lat");
                    double longitude = ob.getDouble("lon");

                    Lugar sitio = new Lugar();
                    sitio.setNombre(nombre);
                    sitio.setSitio(sitioW);
                    sitio.setTelefono(telefono);
                    sitio.setLat(latitude);
                    sitio.setLon(longitude);

                    data.add(sitio);


                }
            }catch(Exception ex){
            ex.printStackTrace();
            }

            return data;

            }
}


