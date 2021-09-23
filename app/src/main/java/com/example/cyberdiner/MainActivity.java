package com.example.cyberdiner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.cyberdiner.adapter.PopularFoodAdapter;
import com.example.cyberdiner.adapter.QuickFixesAdapter;
import com.example.cyberdiner.model.PopularFood;
import com.example.cyberdiner.model.QuickFixes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, qfRecycler;
    PopularFoodAdapter popularFoodAdapter;
    QuickFixesAdapter quickFixesAdapter;
    List<QuickFixes> quickFixesList;
    FloatingActionButton fab;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.floatingActionButton2);
        editText=findViewById(R.id.editText);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.pink));
        }

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Vegetable Momos", "₹60", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chicken Nuggets", "₹108", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Paneer Sandwich", "₹50", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Popcorn", "₹15", R.drawable.popularfood4));
        popularFoodList.add(new PopularFood("Pizza Slice", "₹20", R.drawable.popularfood5));
        popularFoodList.add(new PopularFood("Black Coffee", "₹30", R.drawable.popularfood6));

        setPopularRecycler(popularFoodList);

         quickFixesList = new ArrayList<>();
        quickFixesList.add(new QuickFixes("Samosa", "₹10", R.drawable.quickfix1, "4.0", "Pieces: 1"));
        quickFixesList.add(new QuickFixes("Chocolate Cookies", "₹25", R.drawable.quickfix2, "4.7", "Pieces: 4"));
        quickFixesList.add(new QuickFixes("French Fries", "₹20", R.drawable.quickfix3, "4.5", "Size: small"));
        quickFixesList.add(new QuickFixes("Chocobar Icecream", "₹40", R.drawable.quickfix4, "4.2", "40ml Pack"));
        quickFixesList.add(new QuickFixes("Vegetable Cutlet", "₹30", R.drawable.quickfix5, "4.5", "Pieces: 3"));
        quickFixesList.add(new QuickFixes("Noodles", "₹25", R.drawable.quickfix6, "4.2", "1 plate: serves 1-2 people"));

        setQFRecycler(quickFixesList);
        Intent newin = new Intent(this, CartListActivity.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newin);
            }
        });

    }

    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setQFRecycler(List<QuickFixes> quickFixesList) {

        qfRecycler = findViewById(R.id.qf_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutManager.isAutoMeasureEnabled();
        qfRecycler.setLayoutManager(layoutManager);
        qfRecycler.setNestedScrollingEnabled(false);
        quickFixesAdapter = new QuickFixesAdapter(this, quickFixesList);
        qfRecycler.setAdapter(quickFixesAdapter);
    }


}