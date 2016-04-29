package itesm.mx.helppet;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorFragment extends Fragment implements SensorEventListener {
    private ImageButton play, pause, stop;
    private TextView pasos,tiempo, pasosText,minText;
    private Timer timer;
    private int seconds;
    private int minutes;
    boolean paused;
    boolean stopped;
    private Sensor accelerometer;
    private SensorManager manager;
    private int pasitos;
    float stepLength = (float)(160*0.45);
    boolean inicio=true;
    int pasosIni;
    int pasosPause;


    public SensorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        pasitos=25;//0;
        manager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


        play =(ImageButton)getView().findViewById(R.id.play);
        pause =(ImageButton)getView().findViewById(R.id.pause);
        stop =(ImageButton)getView().findViewById(R.id.stop);

        pasos =(TextView)getView().findViewById(R.id.pasos);
        tiempo =(TextView)getView().findViewById(R.id.tiempo);
        pasosText =(TextView)getView().findViewById(R.id.mintext);
        minText =(TextView)getView().findViewById(R.id.pasostext);

        pause.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);
        pasos.setVisibility(View.INVISIBLE);
        tiempo.setVisibility(View.INVISIBLE);
        pasosText.setVisibility(View.INVISIBLE);
        minText.setVisibility(View.INVISIBLE);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!paused) {
                    paused = true;

                } else {
                    paused = false;
                }
                System.out.println("paused is "+paused);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!stopped){
                    stopped=true;
                    pause.setVisibility(View.INVISIBLE);
                    stop.setVisibility(View.INVISIBLE);
                    pasos.setVisibility(View.INVISIBLE);
                    tiempo.setVisibility(View.INVISIBLE);
                    pasosText.setVisibility(View.INVISIBLE);
                    minText.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.VISIBLE);
                    timer.cancel();
                    System.out.println("hoy recorriste aproximadamente "+ pasitos * stepLength);
                }
                else{
                    stopped=false;
                }
                minutes=0;
                seconds=0;
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                stop.setVisibility(View.VISIBLE);
                pasos.setVisibility(View.VISIBLE);
                tiempo.setVisibility(View.VISIBLE);
                pasosText.setVisibility(View.VISIBLE);
                minText.setVisibility(View.VISIBLE);

                stopped=false;
                paused=false;

                timer=new Timer();
                seconds =0;
                minutes =0;

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (!paused) {
                                        seconds = seconds + 1;
                                        if (seconds >= 60) {
                                            minutes++;
                                            seconds = 0;
                                        }
                                        String min = "";
                                        String sec = "";
                                        if (String.valueOf(minutes).length() == 1) {
                                            min = "0" + minutes;
                                        } else {
                                            min = minutes + "";
                                        }
                                        if (String.valueOf(seconds).length() == 1) {
                                            sec = "0" + seconds;
                                        } else {
                                            sec = seconds + "";
                                        }

                                        tiempo.setText(min + ":" + sec);
                                    }
                                }
                            });
                        }catch(NullPointerException e){

                        }
                    }
                }, 0, 1000);


            }
        });


        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println("sensor");
        float[] datos = event.values;
        if (datos.length > 0) {
            if(inicio || stopped){
                pasosIni= (int) datos[0];
                inicio=false;
            }
            else {
                pasitos = (int) datos[0] - pasosIni;
                pasos.setText(pasitos + "");
            }

        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

           /* float[] datos = event.values;
            if (datos.length > 0) {
                if(inicio || stopped){
                    pasosIni= (int) datos[0];
                    inicio=false;
                }
                else {
                    pasitos = (int) datos[0] - pasosIni;
                    pasos.setText(pasitos + "");
                }

            }*/
        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor, container, false);
    }

}
