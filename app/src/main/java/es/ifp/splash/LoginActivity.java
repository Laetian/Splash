package es.ifp.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {


    protected Button boton1;
    protected Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        final EditText label1= (EditText) findViewById(R.id.label1_login);
        final EditText pass1= (EditText) findViewById(R.id.pass1_login);
        boton1=(Button) findViewById(R.id.boton1_login);
        boton2=(Button) findViewById(R.id.boton2_login);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario = label1.getText().toString();
                final String clave = pass1.getText().toString();
                Response.Listener<String> respuesta= new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if(ok==true)
                            {
                                String usuario= jsonRespuesta.getString("usuario");
                                int level= jsonRespuesta.getInt("level");
                                if(level==1 || level==0)
                                {
                                    Intent secondActivity = new Intent ( LoginActivity.this, SecondActivity.class);
                                    secondActivity.putExtra("usuario", usuario);
                                    secondActivity.putExtra("level", level);



                                    LoginActivity.this.startActivity(secondActivity);
                                    LoginActivity.this.finish();
                                }
                                if(level==2)
                                {
                                    Toast.makeText(LoginActivity.this, "Cuenta aún no activada, contacte con el administrador", Toast.LENGTH_LONG).show();
                                }

                            }

                            else
                                {
                                    Toast.makeText(LoginActivity.this, "no pase por el if", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
                                    alerta.setMessage("Fallo en el acceso").setNegativeButton("Reintentar", null).create().show();
                                }


                        }
                        catch (JSONException e){
                            e.getMessage();
                            Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }

                    }


                };
                LoginRequest r = new LoginRequest(usuario, clave, respuesta);
                RequestQueue cola= Volley.newRequestQueue(LoginActivity.this);
                cola.add(r);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro= new Intent(LoginActivity.this, RegistroActivity.class );
                LoginActivity.this.startActivity(registro);
                finish();
            }
        });


    }
}