package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText user, password;
    private HelpPetDAO helpPetDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpPetDAO = new HelpPetDAO(this);

        setContentView(R.layout.activity_login);
        //        mydb = new DBHelper(this);
        loadFindViewById();
    }
    public void loadFindViewById(){
        user= (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
    }

    public void login(View view){
        Intent it= new Intent(this, NavigationActivity.class);
        String userStr = user.getText().toString();
        String passStr = password.getText().toString();



        if(userStr.length()>0 && passStr.length()>0){
            helpPetDAO.open();
            Usuario u = helpPetDAO.getUsuario(userStr);
            // mydb.getUsuario(userStr);

            if(u!= null) {
                if(u.getPassword().equals(passStr)) {
                    Toast.makeText(this, R.string.bienvenido, Toast.LENGTH_LONG).show();

                    it.putExtra("user", userStr);
                    startActivity(it);
                    finish();
                }
                else{
                    Toast.makeText(this, R.string.contraincorrecta, Toast.LENGTH_LONG).show();

                }
            }
            else{
                Toast.makeText(this, R.string.usuarionoexiste, Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(this, R.string.llenacampos, Toast.LENGTH_LONG).show();
        }




    }


    private int VALOR_SUBACTIVIDAD = 5007;
    public void signin(View view) {
        //helpPetDAO.open();
        //helpPetDAO.dropUsuario();
        Intent it = new Intent(this, SignInSubActivity.class);
        startActivityForResult(it, VALOR_SUBACTIVIDAD);
    }



}
