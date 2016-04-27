package itesm.mx.helppet;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraficaSensorFragment extends Fragment {

    private BarChart barChart;
    private BarDataSet barDataSet;

    //Colecciones de datos y de etiquetas;
    private ArrayList<BarEntry> entries;
    private ArrayList<String> labels;

    public GraficaSensorFragment() {
        // Required empty public constructor
    }
    public void loadEmptyDataSet() {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

        entries = new ArrayList<>();
        labels = new ArrayList<>();
        barDataSet = new BarDataSet(entries, "Pasos");

        ArrayList<DatosSensor> m = listaPasos("datos_sensor");
        System.out.println(m);

        for(int i=0; i<m.size();i++){
            System.out.println(m.get(i).getPasos());
            entries.add(new BarEntry(m.get(i).getPasos()/10,i));
            labels.add(simpleDateFormat.format(m.get(i).getFecha())+"");

        }




        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        barDataSet.setColors(colors);


        BarData barData = new BarData(labels, barDataSet);
        barChart.setData(barData);
        barChart.getAxisLeft().setAxisMaxValue(100f);
        barChart.getAxisLeft().setAxisMinValue(0f);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        FrameLayout frameLayout =(FrameLayout)getView().findViewById(R.id.grafica);
        barChart = new BarChart(getActivity());
        frameLayout.addView(barChart);
        loadEmptyDataSet();

        super.onActivityCreated(savedInstanceState);
    }

    protected ArrayList<DatosSensor> listaPasos(String arch) {
        // TODO Auto-generated method stub
        String archivo = arch;
        AssetManager manager = getActivity().getApplicationContext().getAssets();
        BufferedReader lector = null;
        ArrayList<DatosSensor> resultado = new ArrayList<>();
        try{
            InputStreamReader input =  new InputStreamReader( manager.open(archivo + ".json") );

            lector = new BufferedReader(input);
            String data = "";
            while(lector.ready()){
                data += lector.readLine();
            }
            lector.close();

            Log.i("Data", data);
            resultado = procesaJSON(data);
        }catch(Exception ex ){
            ex.printStackTrace();
        }


        return resultado;
    }
    public ArrayList<DatosSensor> procesaJSON(String archivo){
        ArrayList<DatosSensor> data = new ArrayList<>();
        try{
            JSONObject object = new JSONObject(archivo);
            JSONArray coleccion = object.getJSONArray("data");
            for (int i = 0; i < coleccion.length(); i++) {
                JSONObject ob = coleccion.getJSONObject(i);
                int pasos = ob.getInt("pasos");
                String fecha = ob.getString("fecha");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

                DatosSensor c = new DatosSensor();
                c.setPasos(pasos);
                c.setFecha(simpleDateFormat.parse(fecha));

                data.add(c);

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grafica_sensor, container, false);
    }

}
