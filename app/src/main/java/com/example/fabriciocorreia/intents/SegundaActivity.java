package com.example.fabriciocorreia.intents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SegundaActivity extends Activity {
    int total;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Bundle extras = getIntent().getExtras();
        total =+ extras.getInt("total");
        TextView txvMudou2 =(TextView)findViewById(R.id.txvMudou2);
        txvMudou2.setText("Mudou "+total+" vezes");
        Button btLigar = (Button) findViewById(R.id.btLigar);
        Button btMudar2 =(Button)findViewById(R.id.btMudar2);
        final Intent primeiraActivity = new Intent(this, PrimeiraActivity.class);
        preferences = getSharedPreferences("peferences",0);
        btMudar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementTotal();
                primeiraActivity.putExtra("total",total);
                startActivity(primeiraActivity);
            }
        });
        btLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent ligar = new Intent(Intent.ACTION_CALL, Uri.parse("tel:34936487"));
                startActivity(ligar);
            }
        });
    }
    public void incrementTotal(){
        total++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("total", total);
        editor.commit();
    }

}
