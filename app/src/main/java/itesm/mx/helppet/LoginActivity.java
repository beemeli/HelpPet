package itesm.mx.helppet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText user, password;
    private HelpPetDAO helpPetDAO;
    private DBHelper helper=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpPetDAO = new HelpPetDAO(this);

        setContentView(R.layout.activity_login);
        //        mydb = new DBHelper(this);
        loadFindViewById();

        helper = getHelper(this, DBHelper.class);

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


            try {
                Dao daoUsuario = getHelper(this, DBHelper.class).getUsuarioDao();

                List<Usuarios> mas = daoUsuario.queryForAll();
                boolean flag=false;
                for(Usuarios u: mas){
                    if(u.getId().equals(userStr) && u.getPassword().equals(passStr)){
                        flag=true;
                    }
                    System.out.println(u.getId() + "--"+ u.getPassword());
                }
                if(flag){
                    Toast.makeText(this, R.string.bienvenido, Toast.LENGTH_LONG).show();

                    it.putExtra("user", userStr);
                    startActivity(it);
                    finish();
                }
                else{
                    Toast.makeText(this, R.string.contraincorrecta, Toast.LENGTH_LONG).show();

                }

            }catch(SQLException e){

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
    //Metodos DB
    private DBHelper getHelper(LoginActivity loginActivity, Class<DBHelper> dbHelperClass) {
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
