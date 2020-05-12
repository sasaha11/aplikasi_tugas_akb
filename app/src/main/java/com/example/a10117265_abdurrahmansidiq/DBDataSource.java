package com.example.a10117265_abdurrahmansidiq;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBDataSource {
    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_NIM,
            DBHelper.COLUMN_NAMA, DBHelper.COLUMN_KELAS,DBHelper.COLUMN_TELEPON,DBHelper.COLUMN_EMAIL,DBHelper.COLUMN_SOSIALMEDIA};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    public daftar createDaftar(String nim, String nama, String kelas, String telepon, String email, String sosialmedia) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NIM, nim);
        values.put(DBHelper.COLUMN_NAMA, nama);
        values.put(DBHelper.COLUMN_KELAS, kelas);
        values.put(DBHelper.COLUMN_TELEPON, telepon);
        values.put(DBHelper.COLUMN_EMAIL, email);
        values.put(DBHelper.COLUMN_SOSIALMEDIA, sosialmedia);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_NIM + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek barang
        daftar newDaftar = cursorToDaftar(cursor);

        // close cursor
        cursor.close();

        // mengembalikan barang baru
        return newDaftar;
    }
    private daftar cursorToDaftar(Cursor cursor)
    {
        // buat objek barang baru
        daftar daftar = new daftar();
        // debug LOGCAT
        Log.v("info", "The setLatLng "+cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4)+","+cursor.getString(5));

        /* Set atribut pada objek barang dengan
         * data kursor yang diambil dari database*/
        daftar.setNim(cursor.getString(0));
        daftar.setNama(cursor.getString(1));
        daftar.setKelas(cursor.getString(2));
        daftar.setTelepon(cursor.getString(3));
        daftar.setEmail(cursor.getString(4));
        daftar.setSosialmedia(cursor.getString(5));

        //kembalikan sebagai objek barang
        return daftar;
    }

    public ArrayList<daftar> getAllDaftar() {
        ArrayList<daftar> daftart = new ArrayList<daftar>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data barang ke
        // daftar barang
        while (!cursor.isAfterLast()) {
            daftar Daftar = cursorToDaftar(cursor);
            daftart.add(Daftar);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftart;
    }
    public daftar getDaftar(String nim)
    {
        daftar daftar = new daftar();
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "nim ="+nim, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek barang
        daftar = cursorToDaftar(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return daftar;
    }
    public void updateDaftar(daftar d)
    {
        //ambil id barang
        String strFilter = "nim=" + d.getNim();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA, d.getNama());
        args.put(DBHelper.COLUMN_KELAS, d.getKelas());
        args.put(DBHelper.COLUMN_TELEPON, d.getTelepon());
        args.put(DBHelper.COLUMN_EMAIL, d.getEmail());
        args.put(DBHelper.COLUMN_SOSIALMEDIA, d.getSosialmedia() );
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }
    public void deleteDaftar(String nim)
    {
        String strFilter = "nim=" + nim;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }
}
//11-05-2020,10117265,AbdurrahmanSidiq,IF-8