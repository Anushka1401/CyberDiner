package com.example.cyberdiner.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyberdiner.CartDB;
import com.example.cyberdiner.CartListActivity;
import com.example.cyberdiner.R;
import com.example.cyberdiner.model.CartList;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    private List<CartList> cartList;
    private CartDB cartDB;
    int fquantity, fpricee, sum=0;
    String log="Alert";
    TextView finalsum;
    int number;
    private OnEditTextChanged onEditTextChanged;

    public CartAdapter(Context context, List<CartList> cartList, OnEditTextChanged onEditTextChanged) {
        this.context = context;
        this.cartList = cartList;
        this.onEditTextChanged = onEditTextChanged;
    }

    public interface OnEditTextChanged {
        void onTextChanged(int position, String charSeq);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        cartDB = new CartDB(context);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CartViewHolder holder, int position) {

        holder.foodImage.setImageResource(cartList.get(position).getImageUrl());
        holder.name.setText(cartList.get(position).getName());
        holder.price.setText(cartList.get(position).getPrice().substring(1));
        holder.quantity.setText(cartList.get(position).getQuantity());

        fquantity=Integer.parseInt(cartList.get(position).getQuantity());
        fpricee=Integer.parseInt(cartList.get(position).getPrice().substring(1));

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fquantity+=1;
                switch(holder.name.getText().toString()){
                    case "Vegetable Momos": fpricee = fquantity*60;
                    break;
                    case "Chicken Nuggets": fpricee=fquantity*108;
                    break;
                    case "Paneer Sandwich": fpricee=fquantity*50;
                    break;
                    case "Popcorn": fpricee=fquantity*15;
                    break;
                    case "Pizza Slice": fpricee=fquantity*20;
                    break;
                    case "Black Coffee": fpricee=fquantity*30;
                    break;
                    case "Samosa": fpricee=fquantity*10;
                    break;
                }
                holder.quantity.setText(String.valueOf(fquantity));
                holder.price.setText(String.valueOf(fpricee));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fquantity-=1;
                if (fquantity<0){fquantity=0;}
                switch(holder.name.getText().toString()){
                    case "Vegetable Momos": fpricee = fquantity*60;
                        break;
                    case "Chicken Nuggets": fpricee=fquantity*108;
                        break;
                    case "Paneer Sandwich": fpricee=fquantity*50;
                        break;
                    case "Popcorn": fpricee=fquantity*15;
                        break;
                    case "Pizza Slice": fpricee=fquantity*20;
                        break;
                    case "Black Coffee": fpricee=fquantity*30;
                        break;
                    case "Samosa": fpricee=fquantity*10;
                        break;
                    case "Chocolate Cookies": fpricee=fquantity*25;
                        break;
                    case "French Fries": fpricee=fquantity*20;
                        break;
                    case "Chocobar Icecream": fpricee=fquantity*40;
                        break;
                    case "Vegetable Cutlet": fpricee=fquantity*30;
                        break;
                    case "Noodles": fpricee=fquantity*25;
                        break;
                }
                holder.quantity.setText(String.valueOf(fquantity));
                holder.price.setText(String.valueOf(fpricee));
            }
        });
        holder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onEditTextChanged.onTextChanged(position, charSequence.toString());
                number=Integer.parseInt(holder.price.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        number=Integer.parseInt(holder.price.getText().toString());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage, minus, plus, cartdel;
        TextView price, name, quantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.img_food);
            price = itemView.findViewById(R.id.foodprice);
            name = itemView.findViewById(R.id.foodname);
            quantity = itemView.findViewById(R.id.foodqty);

            cartdel = itemView.findViewById(R.id.cartdel);
            minus=itemView.findViewById(R.id.minus);
            plus=itemView.findViewById(R.id.plus);

            cartdel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Log.e(log,String.valueOf(position));
                    final CartList cartItem = cartList.get(position);
                    cartDB.remove_fav(cartItem.getName());
                    removeItem(position);
                }
            });
        }
    }

    private void removeItem(int position) {

        View v=LayoutInflater.from(context).inflate(R.layout.cart_layout,null);
        CartViewHolder holder1 = new CartViewHolder(v);
        finalsum = ((CartListActivity)context).findViewById(R.id.txt_final_price);
        sum=Integer.parseInt(finalsum.getText().toString())-Integer.parseInt(cartList.get(position).getPrice().substring(1));
        Log.e(log,finalsum.getText().toString()+"-"+cartList.get(position).getPrice().substring(1));
        finalsum.setText(String.valueOf(sum));
        cartList.remove(position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position,cartList.size());
    }

}
