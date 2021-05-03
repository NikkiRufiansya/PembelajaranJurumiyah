package com.khil.pembelajaranjurumiyah.model;
import android.content.Intent;

import java.io.Serializable;

public class DataModel implements Serializable {
    Integer viewType;
    String konten;

    public String getKonten(){
        return konten;
    }

    public void setKonten(String konten) {
        this.konten=konten;
    }

    public Integer getViewType(){
        return viewType;
    }

    public void setViewType (Integer viewType){
        this.viewType = viewType;
    }
}
