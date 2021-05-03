package com.khil.pembelajaranjurumiyah.quiz;

import android.provider.BaseColumns;

public final class QuizContract {

    public QuizContract(){
    }

    public static class TabelPertanyaan implements BaseColumns {

        public static final String NAMA_TABEL = "tabel_pertanyaan";
        public static final String KOLOM_PERTANYAAN = "pertanyaan";
        public static final String KOLOM_OPSI1 = "opsi1";
        public static final String KOLOM_OPSI2 = "opsi2";
        public static final String KOLOM_OPSI3 = "opsi3";
        public static final String KOLOM_OPSI4 = "opsi4";
        public static final String KOLOM_JAWAB = "jawab";
        public static final String BAB = "bab";
    }

    public static class TabelBab implements BaseColumns {
        public static final String NAMA_TABELl = "tabel_bab";
        public static final String BAB = "bab";
    }

}