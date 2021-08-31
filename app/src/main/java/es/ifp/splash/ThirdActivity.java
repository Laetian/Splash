package es.ifp.splash;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {


    protected TextView label1;
    protected TextView label2;
    protected TextView label3;
    protected TextView label4;
    protected TextView label5;
    protected TextView label6;
    protected TextView label7;

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
        setContentView(R.layout.activity_third);

        label1 = (TextView) findViewById(R.id.label1_third);
        label2 = (TextView) findViewById(R.id.label2_third);
        label3 = (TextView) findViewById(R.id.label3_third);
        label4 = (TextView) findViewById(R.id.label4_third);
        label5 = (TextView) findViewById(R.id.label5_third);
        label6 = (TextView) findViewById(R.id.label6_third);
        label7 = (TextView) findViewById(R.id.label7_third);


        caja1 = (EditText) findViewById(R.id.caja1_third);
        caja2 = (EditText) findViewById(R.id.caja2_third);
        caja3 = (EditText) findViewById(R.id.caja3_third);
        caja4 = (EditText) findViewById(R.id.caja4_third);
        caja5 = (EditText) findViewById(R.id.caja5_third);
        caja6 = (EditText) findViewById(R.id.caja6_third);

        boton1 = (Button) findViewById(R.id.boton1_third);


        Intent i = this.getIntent();
        String usuario = i.getStringExtra("usuario");
        label7.setText(usuario);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPlaca("https://travesiaelescorialnavacerrada.com/escolta/guardar.php");
            }
        });
    }
    private void guardarPlaca(String URL){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ThirdActivity.this, "Guardado correcto", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("matricula",caja1.getText().toString());
                parametros.put("modelo",caja2.getText().toString());
                parametros.put("color",caja3.getText().toString());
                parametros.put("ubicacion",caja4.getText().toString());
                parametros.put("intervencion",caja5.getText().toString());
                parametros.put("comentarios",caja6.getText().toString());
                parametros.put("escolta",label7.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_third, menu);

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