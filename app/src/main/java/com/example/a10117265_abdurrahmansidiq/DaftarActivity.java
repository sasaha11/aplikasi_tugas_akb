package com.example.a10117265_abdurrahmansidiq;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DaftarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener , AdapterView.OnItemLongClickListener {
    private DrawerLayout drawer;
    private Button bTambah;
    private Button editButton;
    private Button delButton;
    private Button liButton;
    private DBDataSource dataSource;
    private ArrayList<daftar> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        Toolbar menuToolbar = findViewById(R.id.menuToolBar);
        getSupportActionBar().setTitle("Daftar Teman");

        drawer = findViewById(R.id.drawer_daftar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, menuToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bTambah = (Button) findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllDaftar();

        // masukkan data barang ke array adapter
        ArrayAdapter<daftar> adapter = new ArrayAdapter<daftar>(this,
                android.R.layout.simple_list_item_1, values);

        ListView listView = findViewById(R.id.list);
        // set adapter pada list
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);

}
    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos,
                                   final long id) {

        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        ListView listView = findViewById(R.id.list);
        final daftar d = (daftar) listView.getAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit);
        delButton = (Button) dialog.findViewById(R.id.button_delete);
        liButton = (Button) dialog.findViewById(R.id.button_lihat);

        //apabila tombol edit diklik
        editButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(d.getNim());
                        dialog.dismiss();
                    }
                }
        );
        //apabila tombol delete di klik
        delButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // Delete barang
                        dataSource.deleteDaftar(d.getNim());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        liButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToGetData(d.getNim());
                        dialog.dismiss();
                    }
                }
        );
        return true;
    }
    public void switchToEdit(String nim)
    {
        daftar d = dataSource.getDaftar(nim);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putString("nim", d.getNim());
        bun.putString("nama", d.getNama());
        bun.putString("kelas", d.getKelas());
        bun.putString("telepon", d.getTelepon());
        bun.putString("email", d.getEmail());
        bun.putString("sosialmedia", d.getSosialmedia());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    public void switchToGetData(String nim) {
        daftar d = dataSource.getDaftar(nim);
        Intent i = new Intent(this, LihatData.class);
        Bundle bun = new Bundle();
        bun.putString("nim", d.getNim());
        bun.putString("nama", d.getNama());
        bun.putString("kelas", d.getKelas());
        bun.putString("telepon", d.getTelepon());
        bun.putString("email", d.getEmail());
        bun.putString("sosialmedia", d.getSosialmedia());
        i.putExtras(bun);
        dataSource.close();
        startActivity(i);
    }
    public void finale()
    {
        DaftarActivity.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profile :
                Intent intent = new Intent(DaftarActivity.this,ProfilActivity.class);
                startActivity(intent);
                break;

            case R.id.kontak :
                Intent intent1 = new Intent(DaftarActivity.this,KontakActivity.class);
                startActivity(intent1);
                break;

            case R.id.daftar_teman :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.exit :
                finish();
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button_tambah :
                Intent i = new Intent(this, CreateData.class);
                startActivity(i);
                break;

        }
    }
}
//12-05-2020,10117265,AbdurrahmanSidiq,IF-8