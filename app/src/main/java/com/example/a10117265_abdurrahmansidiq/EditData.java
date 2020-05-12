package com.example.a10117265_abdurrahmansidiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditData extends AppCompatActivity implements View.OnClickListener {
    private DBDataSource dataSource;

    private String nim;
    private String nama;
    private String kelas;
    private String telepon;
    private String email;
    private String sosialmedia;

    private EditText edNama;
    private EditText edKelas;
    private EditText edTelepon;
    private EditText edEmail;
    private EditText edSosialmedia;

    private TextView textnim;

    private Button btnSave;
    private Button btnCancel;

    private daftar daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        edNama = (EditText) findViewById(R.id.editText2);
        edKelas = (EditText) findViewById(R.id.editText3);
        edTelepon = (EditText) findViewById(R.id.editText4);
        edEmail = (EditText) findViewById(R.id.editText5);
        edSosialmedia = (EditText) findViewById(R.id.editText6);
        textnim = (TextView) findViewById(R.id.textViewNim);
        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();
        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        nim = bun.getString("nim");
        nama = bun.getString("nama");
        kelas = bun.getString("kelas");
        telepon = bun.getString("telepon");
        email = bun.getString("email");
        sosialmedia = bun.getString("sosialmedia");
        //masukkan data-data barang tersebut ke field editor
        textnim.setText(nim);
        edNama.setText(nama);
        edKelas.setText(kelas);
        edTelepon.setText(telepon);
        edEmail.setText(email);
        edSosialmedia.setText(sosialmedia);

        //set listener pada tombol
        btnSave = (Button) findViewById(R.id.button_edit);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel);
        btnCancel.setOnClickListener(this);
    }
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_edit :
                daftar = new daftar();
                daftar.setNama(edNama.getText().toString());
                daftar.setKelas(edKelas.getText().toString());
                daftar.setTelepon(edTelepon.getText().toString());
                daftar.setEmail(edEmail.getText().toString());
                daftar.setSosialmedia(edSosialmedia.getText().toString());
                daftar.setNim(nim);
                dataSource.updateDaftar(daftar);
                Intent i = new Intent(this, DaftarActivity.class);
                startActivity(i);
                EditData.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel:
                finish();
                dataSource.close();
                break;
        }
    }
}
//11-05-2020,10117265,AbdurrahmanSidiq,IF-8