package com.khil.pembelajaranjurumiyah.materi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khil.pembelajaranjurumiyah.R;
import com.khil.pembelajaranjurumiyah.adapter.MateriAdapter;
import com.khil.pembelajaranjurumiyah.model.MateriModel;

import java.util.ArrayList;
import java.util.List;

public class ListMateri extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<MateriModel> materiModelList = new ArrayList<>();
    private MateriAdapter materiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_materi);
        recyclerView = findViewById(R.id.recyleView);
        materiModelList.add(new MateriModel(1, "Materi 1"));
        materiModelList.add(new MateriModel(2, "Materi 2"));
        materiModelList.add(new MateriModel(3, "Materi 3"));
        materiModelList.add(new MateriModel(4, "Materi 4"));
        materiModelList.add(new MateriModel(5, "Materi 5"));
        materiModelList.add(new MateriModel(6, "Materi 6"));
        materiModelList.add(new MateriModel(6, "Materi 7"));
        materiAdapter = new MateriAdapter(this, materiModelList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(materiAdapter);
    }
}