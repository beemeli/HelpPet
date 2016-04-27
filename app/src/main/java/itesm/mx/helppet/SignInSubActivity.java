package itesm.mx.helppet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInSubActivity extends AppCompatActivity {
    private EditText user;
    private EditText contra1;
    private EditText contra2;
    private HelpPetDAO mydb;

   // private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sub);
        user = (EditText)findViewById(R.id.nombredr);
        contra1 = (EditText)findViewById(R.id.contraS);
        contra2 = (EditText)findViewById(R.id.contraS2);
        mydb = new HelpPetDAO(this);

        // mydb = new DBHelper(this);


    }


    public void registrate(View view) {

        if(user.getText().toString().length()>0 && contra1.getText().toString().length()>0 && contra2.getText().toString().length()>0){
           // if(user.getText().toString().contains("@")){
                if(contra1.getText().toString().equals(contra2.getText().toString())){
                    System.out.println(user.getText().toString()+ " ************"+contra1.getText().toString());
                    mydb.open();
                    System.out.println(mydb +"***********///////////***");
                    if(mydb.insertUsuario(new Usuario(user.getText().toString(),contra1.getText().toString())) !=-1){

                        Toast.makeText(this, R.string.registrook, Toast.LENGTH_LONG).show();
                        Intent it = new Intent();
                        setResult(RESULT_OK, it);
                        finish();

                    }
                    else{
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
}
