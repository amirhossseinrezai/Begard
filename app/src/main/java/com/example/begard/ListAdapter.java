package com.example.begard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
    return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Data.dta.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView,textView1;
        private ImageView imageView;
        public ListViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.txtTitle);
            textView1 = itemView.findViewById(R.id.txtDescription);
            imageView = itemView.findViewById(R.id.imgContent);
            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            textView.setText(Data.dta[position]);
            textView1.setText("Description");
            imageView.setImageResource(R.drawable.begard);
        }
        public void onClick(View view){

        }
    }
}
