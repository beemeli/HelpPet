package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AprendeActivity extends AppCompatActivity {
    private String user;
    Animation animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprende);
        user = getIntent().getStringExtra("email");
        animTranslate= AnimationUtils.loadAnimation(this, R.anim.anim_translate);



    }

    public void antes(View view) {
        //view.startAnimation(animTranslate);

        Intent it= new Intent(this, AprendeArticulosActivity.class);
        it.putExtra("user", user);
        it.putExtra("opcion", "antes");
        startActivity(it);
    }

    public void cuidados(View view) {
        Intent it= new Intent(this, AprendeArticulosActivity.class);
        it.putExtra("user", user);
        it.putExtra("opcion", "cuidados");
        startActivity(it);
    }

    public void viviendo(View view) {
        Intent it= new Intent(this, AprendeArticulosActivity.class);
        it.putExtra("user", user);
        it.putExtra("opcion", "viviendo");
        startActivity(it);
    }
}
