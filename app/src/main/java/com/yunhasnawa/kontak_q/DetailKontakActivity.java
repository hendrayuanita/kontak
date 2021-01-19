package com.yunhasnawa.kontak_q;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// TODO: [Langkah-6a] Import class Entity dan Model

import com.yunhasnawa.kontak_q.entities.Kontak;
import com.yunhasnawa.kontak_q.models.KontakModel;


public class DetailKontakActivity extends AppCompatActivity
{
    // TODO: [Langkah-6b] Property untuk model dan Kontak yang id nya dipilih untuk ditampilkan

    // Data
    private KontakModel mKontak;
    private Kontak selectedKontak;


    // Komponen
    private EditText txtNama;
    private EditText txtNomor;
    private Button btnUbah;
    private Button btnHapus;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontak);

        this.initData();
        this.initComponents();
    }

    private void initData()
    {
        // Ambil ID dari Kontak dari Intent
        int selectedContactId = this.getIntent().getIntExtra("selectedContactId", -1);

        // TODO: [Langkah-6c] Pilih salah satu kontak yang id-nya sesuai dengan Id yang didapat dari intent

        this.mKontak = new KontakModel(this);

        this.selectedKontak = this.mKontak.selectOne(selectedContactId);

    }

    private void initComponents()
    {
        this.txtNama = (EditText) this.findViewById(R.id.txtNama);
        this.txtNomor = (EditText) this.findViewById(R.id.txtNomor);
        this.btnUbah = (Button) this.findViewById(R.id.btnUbah);
        this.btnHapus = (Button) this.findViewById(R.id.btnHapus);
        this.btnKembali = (Button) this.findViewById(R.id.btnKembali);

        // TODO: [Langkah-6d] Isi teks yang didapat dari kontak ke komponen

        this.txtNama.setText(this.selectedKontak.getNama());
        this.txtNomor.setText(this.selectedKontak.getNomor());

    }

    public void button_onClick(View view)
    {
        Button b = (Button) view;

        if(b == this.btnUbah)
        {
            this.ubahKontak();
        }
        else if(b == this.btnHapus)
        {
            this.hapusKontak();
        }
        else if(b == this.btnKembali)
        {
            this.finish();
        }
    }

    private void ubahKontak()
    {
        String nama = this.txtNama.getText().toString();
        String nomor = this.txtNomor.getText().toString();

        // TODO: [Langkah-6d] Mengedit kontak cukup dengan mengubah nama pada Entity dan melemparnya ke model

        this.selectedKontak.setNama(nama);
        this.selectedKontak.setNomor(nomor);

        this.mKontak.update(this.selectedKontak);

        this.resetFields("Data berhasil diperbarui!", false);

    }

    private void hapusKontak()
    {
        // TODO: [Langkah-6e] Menghapus kontak juga sama. Cukup melempar Entity terpilih ke model

        this.mKontak.delete(this.selectedKontak);

        this.resetFields("Kontak dihapus..", true);

        this.btnHapus.setEnabled(false);

    }

    private void resetFields(String pesan, boolean clear)
    {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();

        if(clear)
        {
            this.txtNama.setText("");
            this.txtNomor.setText("");
        }
    }
}
