package com.example.fabriciocorreia.intents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class PrimeiraActivity extends AppCompatActivity {
    int total;
    SharedPreferences  preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Intent segundaActivity = new Intent(this, SegundaActivity.class);
        Button btMudar1 = (Button)findViewById(R.id.btMudar1);
        Bundle extras = getIntent().getExtras();

        try{
            total =+ extras.getInt("total");
        }catch(NullPointerException exception){
            try{
                preferences = getSharedPreferences("peferences",0);
                total = preferences.getInt("total", 0);
            }catch(NullPointerException exception2){
                total= 0;
            }
        }
        TextView txvMudou1 =(TextView)findViewById(R.id.txvMudou1);
        txvMudou1.setText("Mudou "+total+" vezes");
        btMudar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTotal();
                segundaActivity.putExtra("total",total);
                startActivity(segundaActivity);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_primeira, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void incrementTotal(){
        total++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("total", total);
        editor.commit();
    }
}
