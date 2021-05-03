package com.khil.pembelajaranjurumiyah.banksoal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khil.pembelajaranjurumiyah.model.Questions;
import com.khil.pembelajaranjurumiyah.quiz.QuizContract;

import java.util.ArrayList;

public class DbBankSoal6 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "banksoal3.db";
    private static final int DATBASE_VERSION = 2;

    private SQLiteDatabase db;
    public DbBankSoal6(Context context) {
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
        Questions q1 = new Questions("1 + 1 = ?", "1", "2", "3", "4", 2, 1);
        TambahPertanyaan(q1);
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.TabelPertanyaan.NAMA_TABEL);
        onCreate(db);
    }
}
