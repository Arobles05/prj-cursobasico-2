package com.example.arobles.proyecto_final_modulo_1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
   private ProgressBar  progressBarCircular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarCircular = (ProgressBar)findViewById(R.id.my_progress_bar_ind);
                new AsyncTask_load().execute();
    }
    public class AsyncTask_load extends AsyncTask<Void,Intent,Void> {
        int progreso;
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Subiendo aplicacion...", Toast.LENGTH_LONG).show();
            progreso = 0;
        }
        @Override
        protected Void doInBackground(Void... params) {
            while(progreso < 50){
                progreso++;
                SystemClock.sleep(10);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_LONG).show();
            Intent newWindows = new Intent(getApplicationContext(), TazadorActivity.class);
            startActivity(newWindows);
        }
    }
}
