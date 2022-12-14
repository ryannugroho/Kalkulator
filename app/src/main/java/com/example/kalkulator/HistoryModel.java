package com.example.kalkulator;

public class HistoryModel {
    public String hitungan;

    public HistoryModel(String hitungan) {
        this.hitungan = hitungan;
    }

    @Override
    public String toString() {
        return hitungan;
    }
}
