package es.ifp.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected TextView label3;
    protected EditText caja1;
    protected Button boton1;
    protected ListView lista1;
    private String contenido="";

    private Intent updateActivity=new Intent();


    private ArrayList<String> placas= new ArrayList<String>();
    private ArrayAdapter<String> adaptador;

    private Thread hilo;
    private Runnable lanzar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        label1= (TextView) findViewById(R.id.label1_second);
        label2= (TextView) findViewById(R.id.label2_second);
        label3= (TextView) findViewById(R.id.label3_second);
        caja1= (EditText) findViewById(R.id.caja1_second);
        boton1= (Button) findViewById(R.id.boton1_second);
        lista1= (ListView) findViewById(R.id.lista1_second);



        Intent i = this.getIntent();
        String usuario = i.getStringExtra("usuario");
        label3.setText("Usuario: "+usuario);
        int level = i.getIntExtra("level", 2);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String matricula = caja1.getText().toString();
                String URL = "https://travesiaelescorialnavacerrada.com/escolta/search.php?busqueda="+matricula;
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsonObject = response.getJSONObject(i);
                                adaptador=new ArrayAdapter<String>( SecondActivity.this, android.R.layout.simple_list_item_1, placas);
                                lista1.setAdapter(adaptador);
                                adaptador.clear();
                                placas.add(jsonObject.getString("matricula"));
                                String modelo=jsonObject.getString("modelo");
                                placas.add(modelo);
                                String color=jsonObject.getString("color");
                                placas.add(color);
                                String ubicacion=jsonObject.getString("ubicacion");
                                placas.add(ubicacion);
                                String intervencion=jsonObject.getString("intervencion");
                                placas.add(intervencion);
                                String comentarios=jsonObject.getString("comentarios");
                                placas.add(comentarios);
                                String escolta=jsonObject.getString("escolta");
                                placas.add(escolta);
                                String idDB=jsonObject.getString("id");


                                lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        contenido=parent.getItemAtPosition(position).toString();

                                            updateActivity = new Intent(SecondActivity.this, UpdateActivity.class);
                                            updateActivity.putExtra("usuario", usuario);
                                            updateActivity.putExtra("matricula", matricula);
                                            updateActivity.putExtra("modelo", modelo);
                                            updateActivity.putExtra("color", color);
                                            updateActivity.putExtra("ubicacion", ubicacion);
                                            updateActivity.putExtra("intervencion", intervencion);
                                            updateActivity.putExtra("comentarios", comentarios);
                                            updateActivity.putExtra("escolta", usuario);
                                            updateActivity.putExtra("id", idDB);
                                            startActivity(updateActivity);


                                    }
                                });


                                Toast.makeText(SecondActivity.this, "Busqueda realizada", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(SecondActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue= Volley.newRequestQueue(SecondActivity.this);
                requestQueue.add(jsonArrayRequest);

            }
        });

       /* placas.add("booma1");
        placas.add("booma2");
        placas.add("booma3");

        */





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_add_second:
                Intent intent1 = new Intent(this, ThirdActivity.class);
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