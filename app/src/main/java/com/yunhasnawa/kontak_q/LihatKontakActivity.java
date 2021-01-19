package com.yunhasnawa.kontak_q;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

// TODO: [Langkah-5a] Import Class Entity dan Model

import com.yunhasnawa.kontak_q.entities.Kontak;
import com.yunhasnawa.kontak_q.models.KontakModel;


import java.util.ArrayList;

public class LihatKontakActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // TODO: [Langkah-5b] Buat property untuk model dan daftar Kontak

    // Data
    private KontakModel mKontak;
    private ArrayList<Kontak> allKontak;

    private ArrayList<String> daftarNama;

    // Komponen
    private ListView lstDaftarKontak;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kontak);

        this.initData();
        this.initComponents();
    }

    private void initData()
    {
        this.daftarNama = new ArrayList<>(); // Yang menampung nama untuk ditampilkan ke ListView

        // TODO: [Langkah-5c] Load semua data kontak dari DB dan masukkan ke array daftar nama

        this.mKontak = new KontakModel(this);

        this.allKontak = this.mKontak.selectAll(); // Semua Kontak masuk kesini.

        for(Kontak k : allKontak)
        {
            this.daftarNama.add(k.getNama()); // Ini hanya berupa String dalam bentuk nama saja
        }

    }

    private void initComponents()
    {
        // Ambil Komponen dari Layout
        this.lstDaftarKontak = (ListView) this.findViewById(R.id.lstDaftarKontak);
        this.btnOk = (Button) this.findViewById(R.id.btnOk);

        // Siapkan ListView dan adapternya
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, this.daftarNama);

        this.lstDaftarKontak.setAdapter(adapter);

        this.lstDaftarKontak.setOnItemClickListener(this);
    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b == this.btnOk)
        {
            this.finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        // TODO: [Langkah-5d] Buka activity detail apabila di klik baris pada ListView oleh user

        int id = this.allKontak.get(i).getId();

        Intent intent = new Intent(this, DetailKontakActivity.class);

        intent.putExtra("selectedContactId", id);

        this.startActivity(intent);

    }
}
