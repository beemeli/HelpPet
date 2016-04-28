package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String user;
    Animation animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getStringExtra("email");
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

    }

    public void irAdopta(View view) {
        view.startAnimation(animTranslate);

        Intent it= new Intent(this, AdoptaActivity.class);
        it.putExtra("user", user);
        startActivity(it);


    }

    public void irAprende(View view) {
        view.startAnimation(animTranslate);

        Intent it= new Intent(this, AprendeActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }

    public void irIdentifica(View view) {
        view.startAnimation(animTranslate);

        Intent it= new Intent(this, IdentificaActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }

    public void irConocelos(View view) {
        view.startAnimation(animTranslate);

        Intent it= new Intent(this, NuestrosPerritosActivity.class);
        it.putExtra("user", user);
        startActivity(it);
    }
}
