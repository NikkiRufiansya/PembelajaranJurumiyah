package com.khil.pembelajaranjurumiyah.materi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.khil.pembelajaranjurumiyah.R;
import com.khil.pembelajaranjurumiyah.adapter.MateriAdapter;
import com.khil.pembelajaranjurumiyah.quiz.QuizActivity;

public class MateriActivity extends AppCompatActivity {
    public int nomorMateri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        nomorMateri = getIntent().getIntExtra("nomorMateri", 0);

        //Toast.makeText(this, String.valueOf(nomorMateri), Toast.LENGTH_SHORT).show();
        Button btPlay = findViewById(R.id.soal);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MateriActivity.this, QuizActivity.class);
                intent.putExtra("nomorMateri", nomorMateri);
                startActivity(intent);
            }
        });


        WebView webView = findViewById(R.id.webViewIstimewa);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        if (nomorMateri == 1){
            String file = "satu.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 2){
            String file = "dua.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 3){
            String file = "tiga.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 4){
            String file = "empat.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 5){
            String file = "lima.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 6){
            String file = "enam.html";
            webView.loadUrl("file:///android_asset/" + file);
        }else if (nomorMateri == 7){
            String file = "dua.html";
            webView.loadUrl("file:///android_asset/" + file);
        }

    }
}