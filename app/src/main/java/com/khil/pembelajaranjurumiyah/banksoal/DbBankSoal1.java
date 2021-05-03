package com.khil.pembelajaranjurumiyah.banksoal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.khil.pembelajaranjurumiyah.model.Questions;
import com.khil.pembelajaranjurumiyah.quiz.QuizContract;

import java.util.ArrayList;

public class DbBankSoal1 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "banksoal1.db";
    private static final int DATBASE_VERSION = 1;

    private SQLiteDatabase db;
    public DbBankSoal1(Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.TabelPertanyaan.NAMA_TABEL + " ( " +
                QuizContract.TabelPertanyaan._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.TabelPertanyaan.KOLOM_PERTANYAAN + " TEXT, " +
                QuizContract.TabelPertanyaan.KOLOM_OPSI1 + " TEXT, " +
                QuizContract.TabelPertanyaan.KOLOM_OPSI2 + " TEXT, " +
                QuizContract.TabelPertanyaan.KOLOM_OPSI3 + " TEXT, " +
                QuizContract.TabelPertanyaan.KOLOM_OPSI4 + " TEXT, " +
                QuizContract.TabelPertanyaan.KOLOM_JAWAB + " INTEGER, " +
                QuizContract.TabelPertanyaan.BAB + " INTEGER " +
                ")";
        String SQL_CREATE_BAB_TABLE = "CREATE TABLE" +
                QuizContract.TabelBab.NAMA_TABELl + " ( " +
                QuizContract.TabelBab.BAB + "INTEGER PRIMARY KEY AUTOINCREMENT " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        bankSoalMateri();
    }

    private void bankSoalMateri() {
        Questions q1 = new Questions("Apa itu kalam?", "Lafad yang tersusun dan dapat memberi kepahaman", "Lafad yang tersusun dan dapat memberi pemikiran", "Lafad tersusun dengan isi pemikiran", "Lafad yang berharap tersusun", 1, 1);
        TambahPertanyaan(q1);

        Questions q2 = new Questions("Apa makna lafal dalam keadaan suatu Kalam ?", "Pengucapan memberikan kefahaman", "Suara yang mengandung huruf hijaiyah", "Suara dengan ide pemikiran", "Suara yang berasal dari manusia", 2, 1);
        TambahPertanyaan(q2);

        Questions q3 = new Questions("Apa makna mufid dalam keadaan suatu Kalam ?", "Kefahaman dari pembicara", "Lafad yang jelas", "Memberi kepahaman ke pendengar", "lafad yang tidak jelas", 3, 1);
        TambahPertanyaan(q3);

        Questions q4 = new Questions("Apa makna murokkab dalam keadaan suatu Kalam ?", "Tersusun", "Terbalik", "Rata", "Berbanding", 1, 1);
        TambahPertanyaan(q4);

        Questions q5 = new Questions("Apa makna wadhi dalam keadaan suatu Kalam ?", "Tersusun", "Lafad yang jelas", "Pengucapan memberikan kepahaman", "Kesengajaan menyampaikan pembicaraan", 4, 1);
        TambahPertanyaan(q5);

        Questions q6 = new Questions("Kalam itu paling sedikit tersusun dari dua , isim atau fiil dan ?", "Fail", "Huruf", "Mubtada'", "Khobar", 2, 1);
        TambahPertanyaan(q6);

        Questions q7 = new Questions("Apa ituman", "anuman", "Huruf", "Mubtada'", "Khobar", 1, 1);
        TambahPertanyaan(q7);

        Questions q8 = new Questions("apa anuman", "ituman", "Huruf", "Mubtada'", "Khobar", 1, 1);
        TambahPertanyaan(q8);

        Questions q9 = new Questions("1 + 5 = ?", "4", "5", "6", "7", 3, 2);
        TambahPertanyaan(q9);

        Questions q10 = new Questions("1 + 6 = ?", "4", "5", "6", "7", 4, 2);
        TambahPertanyaan(q10);
    }
    private void TambahPertanyaan(Questions question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.TabelPertanyaan.KOLOM_PERTANYAAN, question.getQuestion());
        cv.put(QuizContract.TabelPertanyaan.KOLOM_OPSI1, question.getOption1());
        cv.put(QuizContract.TabelPertanyaan.KOLOM_OPSI2, question.getOption2());
        cv.put(QuizContract.TabelPertanyaan.KOLOM_OPSI3, question.getOption3());
        cv.put(QuizContract.TabelPertanyaan.KOLOM_OPSI4, question.getOption4());
        cv.put(QuizContract.TabelPertanyaan.KOLOM_JAWAB, question.getAnswerNr());
//        cv.put(TabelPertanyaan.BAB,question.getAnswerNr());
        db.insert(QuizContract.TabelPertanyaan.NAMA_TABEL, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.TabelPertanyaan.NAMA_TABEL);
        onCreate(db);
    }

    public ArrayList<Questions> getAllQuestions() {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String Projection[] = {
                QuizContract.TabelPertanyaan._ID,
                QuizContract.TabelPertanyaan.KOLOM_PERTANYAAN,
                QuizContract.TabelPertanyaan.KOLOM_OPSI1,
                QuizContract.TabelPertanyaan.KOLOM_OPSI2,
                QuizContract.TabelPertanyaan.KOLOM_OPSI3,
                QuizContract.TabelPertanyaan.KOLOM_OPSI4,
                QuizContract.TabelPertanyaan.KOLOM_JAWAB
//                TabelPertanyaan.BAB
        };


        Cursor c = db.query(QuizContract.TabelPertanyaan.NAMA_TABEL,
                Projection,
                null,
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_PERTANYAAN)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_OPSI1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_OPSI2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_OPSI3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_OPSI4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.TabelPertanyaan.KOLOM_JAWAB)));
//                    question.setBab(c.getInt(c.getColumnIndex(TabelPertanyaan.BAB)));
                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;

    }
}
