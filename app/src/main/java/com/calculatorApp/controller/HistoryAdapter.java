package com.calculatorApp.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.calculatorApp.R;
import com.calculatorApp.model.MainData;
import com.calculatorApp.model.RoomDB;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewAdapter> {
    private List<MainData> list;
    private Context context;

    public HistoryAdapter(Activity context, List<MainData> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.workingT.setText(list.get(position).workingDb);
        holder.resultT.setText(list.get(position).resultDb);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {

        TextView workingT, resultT;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            workingT = itemView.findViewById(R.id.text_view);
            resultT = itemView.findViewById(R.id.text_view2);
        }
    }

}
