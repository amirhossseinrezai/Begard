package com.example.begard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter implements Filterable {
    private onAdapterListener onAdapterListener;
    private  List<Data> listData;
    private List<Data> listFilter;

    public ListAdapter(onAdapterListener onAdapterListener,List<Data> listData){
        this.onAdapterListener=onAdapterListener;
        this.listData = listData;
        this.listFilter = listData;
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
       // Data dta = listData.get(position);
       // String title = dta.getTitle();
       // String description = dta.getDescription();
       // holder.textViewDescription.setText("description");
      //  holder.imageViewContent.setImageResource(R.drawable.begard);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public Filter getFilter() {
        final Filter filter =new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResult = new FilterResults();
                if(charSequence == null | charSequence.length() == 0){
                    filterResult.count = listFilter.size();
                    filterResult.values = listFilter;
                }else{
                    String searchChar = charSequence.toString().toLowerCase();
                    List<Data> resultData = new ArrayList<Data>();
                    for (Data dta: listFilter) {
                            if(dta.getPlacename().toLowerCase().contains(searchChar)){
                                resultData.add(dta);
                            }
                    }
                    filterResult.count = resultData.size();
                    filterResult.values = resultData;
                }
                return filterResult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listData = (List<Data>)filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public  class ListViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle,textViewDescription;
        public ImageView imageViewContent;
        onAdapterListener onAdapterListener;
        public ListViewHolder(View itemView, final onAdapterListener onAdapterListener){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtTitle);
            textViewDescription = itemView.findViewById(R.id.txtDescription);
            imageViewContent = itemView.findViewById(R.id.imgContent);
            this.onAdapterListener = onAdapterListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onAdapterListener.onAdapterListener(listData.get(getAdapterPosition()));
                }
            });
        }
       public void bindView(int position){
            Data dta =listData.get(position);
            String s = dta.getPlacename();
            textViewTitle.setText(s);
            textViewDescription.setText("Description");
            imageViewContent.setImageResource(R.drawable.begard);
        }

    }
    public interface onAdapterListener{
        void onAdapterListener(Data userModel);
    }
}
