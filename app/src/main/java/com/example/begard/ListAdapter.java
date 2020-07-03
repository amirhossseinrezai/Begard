package com.example.begard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter{
    private onAdapterListener onAdapterListener;
    public ListAdapter(onAdapterListener onAdapterListener){
        this.onAdapterListener=onAdapterListener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
    return new ListViewHolder(view,onAdapterListener);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Data.dta.length;
    }
    public static class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textView,textView1;
        private ImageView imageView;
        private onAdapterListener onAdapterListener;
        public ListViewHolder(View itemView,onAdapterListener onAdapterListener){
            super(itemView);
            textView = itemView.findViewById(R.id.txtTitle);
            textView1 = itemView.findViewById(R.id.txtDescription);
            imageView = itemView.findViewById(R.id.imgContent);
            this.onAdapterListener = onAdapterListener;
            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            textView.setText(Data.dta[position]);
            textView1.setText("Description");
            imageView.setImageResource(R.drawable.begard);
        }
        public void onClick(View view){
            onAdapterListener.onAdapterListener(getAdapterPosition());
        }
    }
    public interface onAdapterListener{
        void onAdapterListener(int position);
    }
}
