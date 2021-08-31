package es.ifp.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected ImageView ima1;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1= (TextView) findViewById(R.id.label1_start);
        ima1= (ImageView) findViewById(R.id.ima1_start);

        TimerTask tt= new TimerTask() {
            @Override
            public void run() {
                pasarPantalla= new Intent(StartActivity.this, LoginActivity.class);
                finish();
                startActivity(pasarPantalla);

            }
        };
        Timer t= new Timer();
        t.schedule(tt, 3000);


    }
}