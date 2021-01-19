package com.yunhasnawa.kontak_q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// TODO: [Langkah-4a] Action untuk INSERT & UPDATE (import)

import com.yunhasnawa.kontak_q.entities.Kontak;
import com.yunhasnawa.kontak_q.models.KontakModel;


public class TambahKontakActivity extends AppCompatActivity
{
    // TODO: [Langkah-4b] Import Class Model

    // Data
    private KontakModel mKontak;


    // Komponen
    private EditText txtNama;
    private EditText txtNomor;
    private Button btnSimpan;
    private Button btnBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);

        this.initData();
        this.initComponents();
    }

    private void initData()
    {
        // TODO: [Langkah-4c] Instansiasi class model

        this.mKontak = new KontakModel(this);

    }

    private void initComponents()
    {
        this.txtNama = (EditText) this.findViewById(R.id.txtNama);
        this.txtNomor = (EditText) this.findViewById(R.id.txtNomor);
        this.btnSimpan = (Button) this.findViewById(R.id.btnSimpan);
        this.btnBatal = (Button) this.findViewById(R.id.btnBatal);
    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b == this.btnSimpan)
        {
            this.tambahKontak();
        }
        else if(b == this.btnBatal)
        {
            this.finish();
        }
    }

    private void tambahKontak()
    {
        String nama = this.txtNama.getText().toString();
        String nomor = this.txtNomor.getText().toString();

        // TODO: [Langkah-4d] Script untuk tambah kontak

        Kontak kontakBaru = new Kontak();
        kontakBaru.setNama(nama);
        kontakBaru.setNomor(nomor);

        this.mKontak.insert(kontakBaru);

        Toast.makeText(this, "Kontak berhasil ditambahkan", Toast.LENGTH_SHORT).show();

        this.btnBatal.setText("Kembali");

    }
}
