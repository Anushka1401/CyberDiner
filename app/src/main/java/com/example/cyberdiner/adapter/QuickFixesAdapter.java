package com.example.cyberdiner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyberdiner.DetailsActivity;
import com.example.cyberdiner.R;
import com.example.cyberdiner.model.QuickFixes;

import java.util.List;

public class QuickFixesAdapter extends RecyclerView.Adapter<QuickFixesAdapter.QuickFixesViewHolder> {

    Context context;
    List<QuickFixes> quickFixesList;



    public QuickFixesAdapter(Context context, List<QuickFixes> quickFixesList) {
        this.context = context;
        this.quickFixesList = quickFixesList;
    }

    @NonNull
    @Override
    public QuickFixesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.quick_fixes_row_item, parent, false);
        return new QuickFixesViewHolder(view);
    }

    @Override
    public void onBindViewHolder( QuickFixesViewHolder holder, int position) {

        holder.foodImage.setImageResource(quickFixesList.get(position).getImageUrl());
        holder.name.setText(quickFixesList.get(position).getName());
        holder.price.setText(quickFixesList.get(position).getPrice());
        holder.rating.setText(quickFixesList.get(position).getRating());
        holder.quantity.setText(quickFixesList.get(position).getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("foodname",quickFixesList.get(position).getName());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return quickFixesList.size();
    }


    public static final class QuickFixesViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, quantity;

        public QuickFixesViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            quantity = itemView.findViewById(R.id.quantity);



        }
    }

}