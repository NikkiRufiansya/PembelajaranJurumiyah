package com.khil.pembelajaranjurumiyah.model;

public class MateriModel {
    public int id;
    public String namaMateri;

    public MateriModel(int id, String namaMateri) {
        this.id = id;
        this.namaMateri = namaMateri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }
}
