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

public class RegistroActivity extends AppCompatActivity {

    protected EditText label1;
    protected EditText label2;
    protected Button boton1;
    protected Button boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        label1= (EditText) findViewById(R.id.label1_registro);
        label2= (EditText) findViewById(R.id.label2_registro);
        boton1 = (Button) findViewById(R.id.boton1_registro);
        boton2 = (Button) findViewById(R.id.boton2_registro);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = label1.getText().toString();
                String clave = label2.getText().toString();
                String level ="2";



                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonRespuesta= new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if (ok == true)
                            {
                                Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                                RegistroActivity.this.startActivity(i);
                                RegistroActivity.this.finish();
                            }
                            else
                            {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(RegistroActivity.this);
                                alerta.setMessage("Fallo en el registro")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch(JSONException e)
                        {
                            Toast.makeText(RegistroActivity.this, "error", Toast.LENGTH_SHORT).show();
                            e.getMessage();
                        }
                    }
                };


                DBConnection r = new DBConnection(usuario, clave, level, respuesta);
                RequestQueue cola= Volley.newRequestQueue(RegistroActivity.this);
                cola.add(r);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver= new Intent(RegistroActivity.this, LoginActivity.class );
                RegistroActivity.this.startActivity(volver);
                finish();
            }
        });
    }
}