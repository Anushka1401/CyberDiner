package com.example.cyberdiner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cyberdiner.adapter.CartAdapter;
import com.example.cyberdiner.adapter.PopularFoodAdapter;
import com.example.cyberdiner.model.CartList;
import com.example.cyberdiner.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CartListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recycler_cart;
    TextView txt_final_price;
    Button btn_order;
    int sum=0;
    private CartAdapter cartAdapter;
    private List<CartList> cartList=new ArrayList<>();
    private CartDB cartDB;
    int[] enteredNumber = new int[1000];
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.pink));
        }

        //toolbar=findViewById(R.id.toolbar);
        recycler_cart=findViewById(R.id.recycler_cart);
        txt_final_price=findViewById(R.id.txt_final_price);
        btn_order=findViewById(R.id.btn_order);

        //cartList.add(new CartList(foodname, foodprice, R.drawable.popularfood1, foodqty));

        cartDB = new CartDB(this);
        recycler_cart.setHasFixedSize(true);
        recycler_cart.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        loadData();

    }

    private void loadData() {
        if (cartList != null) {
            cartList.clear();
        }
        SQLiteDatabase db = cartDB.getReadableDatabase();
        Cursor cursor = cartDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_TITLE));
                String price = cursor.getString(cursor.getColumnIndex(CartDB.PRICE));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CartDB.ITEM_IMAGE)));
                String qty = cursor.getString(cursor.getColumnIndex(CartDB.QUANTITY));
                CartList cartItem = new CartList(title, price, image, qty);
                cartList.add(cartItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        cartAdapter = new CartAdapter(this, cartList, new CartAdapter.OnEditTextChanged() {
            @Override
            public void onTextChanged(int position, String charSeq) {
                enteredNumber[position] = Integer.valueOf(charSeq);
                updateTotalValue();
            }
        });

        for (int i=0; i<cartList.size(); i++){
            sum+=Integer.parseInt(cartList.get(i).getPrice().substring(1));
            txt_final_price.setText(String.valueOf(sum));
        }

        recycler_cart.setAdapter(cartAdapter);
    }

    private void updateTotalValue() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += enteredNumber[i];
        }

        txt_final_price.setText(String.valueOf(sum));
    }
}