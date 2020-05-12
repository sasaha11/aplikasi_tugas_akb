package com.example.a10117265_abdurrahmansidiq;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        TextView tvNim = (TextView) findViewById(R.id.tv_nim);
        TextView tvNama = (TextView) findViewById(R.id.tv_nama);
        TextView tvKelas = (TextView) findViewById(R.id.tv_kelas);
        TextView tvTelepon = (TextView) findViewById(R.id.tv_telepon);
        TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        TextView tvSosialmedia = (TextView) findViewById(R.id.tv_sosialmedia);

        System.out.println("APPINVENT "+getIntent().getExtras().getString("nim"));
        tvNim.setText("Nim                : "+getIntent().getExtras().getString("nim"));
        tvNama.setText("Nama             : "+getIntent().getExtras().getString("nama"));
        tvKelas.setText("Kelas              : "+getIntent().getExtras().getString("kelas"));
        tvTelepon.setText("Telepon          : "+getIntent().getExtras().getString("telepon"));
        tvEmail.setText("Email              : "+getIntent().getExtras().getString("email"));
        tvSosialmedia.setText("Sosial Media : "+getIntent().getExtras().getString("sosialmedia"));

        Button buttonOK = (Button) findViewById(R.id.bt_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
//11-05-2020,10117265,AbdurrahmanSidiq,IF-8