package itesm.mx.helppet;

/**
 * Created by beeme on 28/04/2016.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

/**
 * Created by beeme on 14/04/2016.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;

public class ActualizaServicio extends Service {
    private static final String TAG = "InvocaServicio";
    public static final String _ACTION = "itesm.mx.helppet";//
    private final Handler handler = new Handler();
    private Intent intent;

    @Override
    public void onCreate(){
        super.onCreate();
        intent= new Intent(_ACTION);
    }

    String n="";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        n = intent.getStringExtra("nombre");
        handler.removeCallbacks(ejecutaAccion);
        handler.postDelayed(ejecutaAccion,1000);

        return START_STICKY;
    }

    private Runnable ejecutaAccion = new Runnable() {
        @Override
        public void run() {
            mensajeActivity();
            handler.postDelayed(this,30000);
        }
    };

    public void mensajeActivity(){
        intent.putExtra("mensaje",n+new Date().getHours() + ":" + new Date().getMinutes());
        sendBroadcast(intent);
    }
    @Override
    public void onDestroy() {
        handler.removeCallbacks(ejecutaAccion);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
