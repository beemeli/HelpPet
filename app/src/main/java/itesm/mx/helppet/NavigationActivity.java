package itesm.mx.helppet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private String user;
    Animation animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        user = getIntent().getStringExtra("email");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
  /*      if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new MainFragment()).commit();
        }
        else if(id==R.id.nav_view){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new AdoptaFragment()).commit();
        }
        else if(id==R.id.nav_slideshow){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new AprendeFragment()).commit();
        }
        else if(id==R.id.nav_conocelos){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new NuestrosPerritosFragment()).commit();
        }
        else if(id==R.id.nav_identifica){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new IdentificaFragment()).commit();
        }
        else if(id==R.id.nav_sensor){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new SensorFragment()).commit();
        }
        else if(id==R.id.nav_grafica_sensor){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new GraficaSensorFragment()).commit();
        }
        else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new PerfilFragment()).commit();


        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
