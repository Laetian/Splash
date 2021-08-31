package es.ifp.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected TextView label3;
    protected TextView label4;
    protected TextView label5;
    protected TextView label6;

    protected EditText caja1;
    protected EditText caja2;
    protected EditText caja3;
    protected EditText caja4;
    protected EditText caja5;
    protected EditText caja6;

    protected Button boton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        label1 = (TextView) findViewById(R.id.label1_update);
        label2 = (TextView) findViewById(R.id.label2_update);
        label3 = (TextView) findViewById(R.id.label3_update);
        label4 = (TextView) findViewById(R.id.label4_update);
        label5 = (TextView) findViewById(R.id.label5_update);
        label6 = (TextView) findViewById(R.id.label6_update);

        caja1 = (EditText) findViewById(R.id.caja1_update);
        caja2 = (EditText) findViewById(R.id.caja2_update);
        caja3 = (EditText) findViewById(R.id.caja3_update);
        caja4 = (EditText) findViewById(R.id.caja4_update);
        caja5 = (EditText) findViewById(R.id.caja5_update);
        caja6 = (EditText) findViewById(R.id.caja6_update);

        boton1 = (Button) findViewById(R.id.boton1_update);


        Intent i = this.getIntent();
        String usuario = i.getStringExtra("usuario");
        String matricula = i.getStringExtra("matricula");
        String modelo = i.getStringExtra("modelo");
        String color = i.getStringExtra("color");
        String ubicacion = i.getStringExtra("ubicacion");
        String intervencion = i.getStringExtra("intervencion");
        String comentarios = i.getStringExtra("comentarios");
        String escolta1  = i.getStringExtra("escolta");
        String id1  = i.getStringExtra("id");

        caja1.setText(matricula);
        caja2.setText(modelo);
        caja3.setText(color);
        caja4.setText(ubicacion);
        caja5.setText(intervencion);
        caja6.setText(comentarios);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String matricula= caja1.getText().toString();
                final String modelo= caja2.getText().toString();
                final String color= caja3.getText().toString();
                final String ubicacion= caja4.getText().toString();
                final String intervencion= caja5.getText().toString();
                final String comentarios= caja6.getText().toString();
                final String escolta= escolta1+", actualizado: "+usuario;
                final String id=id1;




                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonRespuesta= new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("success");
                            if (ok == true)
                            {
                                Intent i = new Intent(UpdateActivity.this, SecondActivity.class);
                                UpdateActivity.this.startActivity(i);
                                UpdateActivity.this.finish();
                            }
                            else
                            {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(UpdateActivity.this);
                                alerta.setMessage("Fallo en el registro")
                                        .setNegativeButton("Reintentar", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch(JSONException e)
                        {
                            Toast.makeText(UpdateActivity.this, "error", Toast.LENGTH_SHORT).show();
                            e.getMessage();
                        }
                    }
                };


                UpdateRequest r = new UpdateRequest(id, matricula, modelo, color, ubicacion, intervencion, comentarios, escolta, respuesta);
                RequestQueue cola= Volley.newRequestQueue(UpdateActivity.this);
                cola.add(r);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_volver_update:
                Intent intent1 = new Intent(this, SecondActivity.class);
                Intent i = this.getIntent();
                String usuario = i.getStringExtra("usuario");
                intent1.putExtra("usuario", usuario);
                startActivity(intent1);


                return true;
            case R.id.item_exit_all:

                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}