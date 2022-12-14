package com.example.kalkulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder>{
    private ArrayList<HistoryModel> history = new ArrayList<>();

    public AdapterHistory(ArrayList<HistoryModel> history) {
        this.history = history;
    }

    @NonNull
    @Override
    public AdapterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.ViewHolder holder, int position) {
        HistoryModel riwayat = history.get(position);
        holder.hitungan.setText(riwayat.toString());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hitungan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hitungan = itemView.findViewById(R.id.hitungan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    history.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
