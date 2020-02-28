package com.mfcolak.planegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class bitisActivity extends AppCompatActivity {


    private Button tekraroyna;
    private TextView skorPuan;
    private String skor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitis);

        skor = getIntent().getExtras().get("Skor").toString();
        tekraroyna = (Button) findViewById(R.id.tekrar_btn);
        skorPuan = (TextView) findViewById(R.id.skorpuan);

        tekraroyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bitisActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        skorPuan.setText("Skor = " + skor);
    }
}
