package com.example.coronaupdate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronaupdate.model.global.CountriesItem;

import java.util.ArrayList;
import java.util.List;

public class CoronaGlobalAdapter extends RecyclerView.Adapter<CoronaGlobalAdapter.ViewHolder> {
    List<CountriesItem> Items;
    List<CountriesItem> filteredItems;
    String sDate;

    public CoronaGlobalAdapter(List<CountriesItem> items, String sdate) {
        Items = items;
        this.sDate = sdate;
        this.filteredItems = Items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_global, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(filteredItems.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountyName;
        Context context = itemView.getContext();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountyName = itemView.findViewById(R.id.country_name);
        }

        void bind(CountriesItem item){
            tvCountyName.setText(item.getCountry());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailGlobalActivity.class);
                    intent.putExtra(DetailGlobalActivity.EXTRA_DATA, item);
                    intent.putExtra(DetailGlobalActivity.EXTRA_DATE,sDate);
                    context.startActivity(intent);
                }
            });
        }
    }

    public Filter getFilter(String sDate){
        this.sDate = sDate;
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()){
                    filteredItems = Items;
                } else {
                    List<CountriesItem> listFiltered = new ArrayList<>();
                    for(CountriesItem row: Items){
                        if (row.getCountry().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    filteredItems = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (List<CountriesItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}