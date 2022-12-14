package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    ArrayList<HistoryModel> history = new ArrayList<>();
    private RecyclerView recyclerView;

    EditText edtAngka1, edtAngka2;
    Button btnHasil;
    RadioButton tambah, kurang, kali, bagi;
    TextView txtHasil;
    AdapterHistory adapter;
    SharedPreferences.Editor setData;
    SharedPreferences getData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prosesAdapterHistory();

        if(getData.contains("riwayat")){
            txtHasil.setText(getData.getString("riwayat", null));
            addRiwayat();
        }

        btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jikalau();
            }
        });
    }

    private void tambah() {

            float ang1 = Float.parseFloat(String.valueOf(edtAngka1.getText()));
            float ang2 = Float.parseFloat(String.valueOf(edtAngka2.getText()));
            float hasil = (ang1 + ang2);
            txtHasil.setText(ang1 + " + " + ang2 + " = " + hasil);
            addRiwayat();
    }

    private void kurang() {

            float ang1 = Float.parseFloat(String.valueOf(edtAngka1.getText()));
            float ang2 = Float.parseFloat(String.valueOf(edtAngka2.getText()));

            float hasil = (ang1 - ang2);
            txtHasil.setText(ang1 + " - " + ang2 + " = " + hasil);
            addRiwayat();
    }
    private void bagi(){

            float ang1 = Float.parseFloat(String.valueOf(edtAngka1.getText()));
            float ang2 = Float.parseFloat(String.valueOf(edtAngka2.getText()));

            float hasil = (ang1 / ang2);
            txtHasil.setText(ang1 + " : " + ang2 + " = " + hasil);
            addRiwayat();
    }
    private void kali(){

            float ang1 = Float.parseFloat(String.valueOf(edtAngka1.getText()));
            float ang2 = Float.parseFloat(String.valueOf(edtAngka2.getText()));

            float hasil = (ang1 * ang2);
            txtHasil.setText(ang1 + " x " + ang2 + " = " + hasil);
            addRiwayat();
    }

    private void prosesAdapterHistory(){
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AdapterHistory(history);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setData=getSharedPreferences("data", MODE_PRIVATE).edit();
        getData=getSharedPreferences("data", MODE_PRIVATE);

        edtAngka1= findViewById(R.id.edtAngka1);
        edtAngka2= findViewById(R.id.edtAngka2);
        btnHasil= findViewById(R.id.btnHasil);
        tambah= findViewById(R.id.tambah);
        kurang= findViewById(R.id.kurang);
        kali= findViewById(R.id.kali);
        bagi= findViewById(R.id.bagi);
        txtHasil= findViewById(R.id.txtHasil);
    }

    private void jikalau(){
        if (tambah.isChecked()){
            tambah();
        } else if (kurang.isChecked()){
            kurang();
        } else if (kali.isChecked()){
            kali();
        } else if (bagi.isChecked()){
            bagi();
        }
        setSharedPreferences();
    }

    public void resetInput(){
        edtAngka1.setText("");
        edtAngka2.setText("");
    }

    public void addRiwayat(){
        HistoryModel pesanan = new HistoryModel(txtHasil.getText().toString());
        history.add(pesanan);
        adapter.notifyDataSetChanged();
        resetInput();
    }

    public void setSharedPreferences(){
        setData.putString("riwayat", txtHasil.getText().toString());
        setData.apply();
    }
}