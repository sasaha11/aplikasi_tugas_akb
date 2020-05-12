package com.example.a10117265_abdurrahmansidiq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateData extends AppCompatActivity implements View.OnClickListener {
    private Button button_simpan;
    private Button button_cancel_simpan;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);
        button_simpan = (Button) findViewById(R.id.button_simpan);
        button_simpan.setOnClickListener(this);
        button_cancel_simpan = (Button) findViewById(R.id.button_cancel_simpan);
        button_cancel_simpan.setOnClickListener(this);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText6 = (EditText) findViewById(R.id.editText6);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    @Override
    public void onClick(View v) {
        String nim = null;
        String nama = null;
        String kelas = null;
        String telepon = null;
        String email = null;
        String sosialmedia = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
                daftar daftar = null;
        if(editText1.getText()!=null && editText2.getText()!=null && editText3.getText()!=null && editText4.getText()!=null && editText5.getText()!=null && editText6.getText()!=null)
        {
            /* jika field nama, merk, dan harga tidak kosong
             * maka masukkan ke dalam data barang*/
            nim = editText1.getText().toString();
            nama = editText2.getText().toString();
            kelas = editText3.getText().toString();
            telepon = editText4.getText().toString();
            email = editText5.getText().toString();
            sosialmedia = editText6.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.button_simpan:
                Intent i = new Intent(this, DaftarActivity.class);
                startActivity(i);
                // insert data barang baru
                daftar = dataSource.createDaftar(nim, nama, kelas, telepon, email, sosialmedia);
                //konfirmasi kesuksesan
                Toast.makeText(this, "Tambah Teman \n" +
                        "nim" + daftar.getNim() +
                        "nama" + daftar.getNama() +
                        "kelas" + daftar.getKelas()+
                        "telepon" + daftar.getTelepon()+
                        "email" + daftar.getEmail()+
                        "sosialmedia" + daftar.getSosialmedia(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button_cancel_simpan:
                finish();
                dataSource.close();
                break;
        }
    }
}
//12-05-2020,10117265,AbdurrahmanSidiq,IF-8