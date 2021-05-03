package com.khil.pembelajaranjurumiyah.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.khil.pembelajaranjurumiyah.R;
import com.khil.pembelajaranjurumiyah.materi.MateriActivity;
import com.khil.pembelajaranjurumiyah.model.MateriModel;

import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MyView> {

    private Context context;
    private List<MateriModel> data;

    public MateriAdapter(Context context, List<MateriModel> data) {
        this.context = context;
        this.data = data;
    }



    @NonNull
    @Override
    public MateriAdapter.MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_materi,viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.MyView myView, int i) {
        myView.button.setText(data.get(i).getNamaMateri());
        myView.button.setOnClickListener(v -> {
            Intent intent = new Intent(context, MateriActivity.class);
            intent.putExtra("nomorMateri", data.get(i).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        Button button;
        public MyView(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn_materi);
        }
    }
}
