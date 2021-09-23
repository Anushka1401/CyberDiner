package com.example.cyberdiner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyberdiner.model.CartList;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ImageView food, plus, minus, bookmark, back, share, addtocart, waittag;
    TextView rating, saved, order, foodtype, foodName, fooddesc, qty, price;
    ConstraintLayout mainbg;
    int quantity, pricee, clickcounter, save;

    final String log="Alert";
    private List<CartList> cartList;
    private Context context=this;
    private CartDB cartDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.pink));
        }

        cartDB = new CartDB(context);
        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        String foodname = getIntent().getStringExtra("foodname");
        cartList=new ArrayList<>();
        clickcounter=0;
        mainbg=findViewById(R.id.mainbg);
        waittag=findViewById(R.id.imageView9);
        food=findViewById(R.id.imageView8);
        plus=findViewById(R.id.imageView11);
        minus=findViewById(R.id.imageView10);
        bookmark=findViewById(R.id.imageView7);
        back=findViewById(R.id.imageView5);
        share=findViewById(R.id.imageView6);
        addtocart=findViewById(R.id.imageView12);
        rating=findViewById(R.id.textView3);
        saved=findViewById(R.id.textView5);
        order=findViewById(R.id.textView6);
        foodtype=findViewById(R.id.textView10);
        foodName=findViewById(R.id.textView11);
        fooddesc=findViewById(R.id.textView12);
        qty=findViewById(R.id.textView13);
        price=findViewById(R.id.textView14);
        Intent i = new Intent(this, CartListActivity.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (foodname.toLowerCase().equals("vegetable momos")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood1);
            rating.setText("4.9");
            saved.setText("210");
            order.setText("98");
            foodtype.setText("VEGETARIAN FOOD");
            foodName.setText("Vegetable Momos");
            fooddesc.setText("Steamed dumplings stuffed with a lightly spiced vegetable filling");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*60;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*60;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*60;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });

        }
        if (foodname.toLowerCase().equals("chicken nuggets")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood2);
            rating.setText("4.7");
            saved.setText("100");
            order.setText("78");
            foodtype.setText("NON-VEGETARIAN FOOD");
            foodName.setText("Chicken Nuggets");
            fooddesc.setText("Bite-sized pieces of boneless chicken, seasoned to perfection and freshly cooked ");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*108;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*108;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*108;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("paneer sandwich")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood3);
            rating.setText("4.6");
            saved.setText("67");
            order.setText("96");
            foodtype.setText("VEGETARIAN FOOD");
            foodName.setText("Paneer Sandwich");
            fooddesc.setText("Delicious sandwich made with cottage cheese, spices, veggies and herbs ");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*50;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*50;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*50;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("popcorn")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood4);
            rating.setText("4.0");
            saved.setText("37");
            order.setText("55");
            foodtype.setText("VEGETARIAN FOOD");
            foodName.setText("Popcorn");
            fooddesc.setText("Snack made of corn kernels - available plain or buttered ");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*15;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*15;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*15;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("pizza slice")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood5);
            rating.setText("4.8");
            saved.setText("120");
            order.setText("185");
            foodtype.setText("VEGETARIAN FOOD");
            foodName.setText("Pizza Slice");
            fooddesc.setText("A slice of tomato sauce, mozzarella cheese and green peppers cooked to perfection");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*20;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*20;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*20;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("black coffee")){
            mainbg.setBackgroundColor(Color.parseColor("#D6D6D6"));
            food.setImageResource(R.drawable.popularfood6);
            rating.setText("4.5");
            saved.setText("35");
            order.setText("88");
            foodtype.setText("DRINK");
            foodName.setText("Black Coffee");
            fooddesc.setText("Aromatic coffee brewed without additives");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*30;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*30;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*30;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }

        if (foodname.toLowerCase().equals("samosa")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix1);
            rating.setText("4.0");
            saved.setText("25");
            order.setText("190");
            foodtype.setText("VEGETARIAN SNACK");
            foodName.setText("Samosa");
            fooddesc.setText("Fried or baked pastry with a savory filling, such as spiced potatoes, onions, peas and lentils");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*10;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*10;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*10;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("chocolate cookies")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix2);
            rating.setText("4.7");
            saved.setText("30");
            order.setText("70");
            foodtype.setText("SNACK (contains eggs)");
            foodName.setText("Chocolate Cookies");
            fooddesc.setText("A chewy, moist-centered cookie with barely crisp edges, flecked with semi-sweet chocolate chips");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*25;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*25;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*25;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("french fries")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix3);
            rating.setText("4.5");
            saved.setText("35");
            order.setText("60");
            foodtype.setText("VEGETARIAN SNACK");
            foodName.setText("French Fries");
            fooddesc.setText("Deep-fried, very thin, salted slices of potato that are mildly seasoned");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*20;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*20;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*20;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("chocobar icecream")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix4);
            rating.setText("4.2");
            saved.setText("35");
            order.setText("60");
            foodtype.setText("VEGETARIAN SNACK");
            foodName.setText("Chocobar Icecream");
            fooddesc.setText(" Chocobar is made using creamier vanilla ice mix prepared with milk solids, skimmed milk powder, sugar, and cream is then dipped into thickest milk choco dip");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*40;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*40;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*40;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("vegetable cutlet")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix5);
            rating.setText("4.5");
            saved.setText("35");
            order.setText("60");
            foodtype.setText("VEGETARIAN SNACK");
            foodName.setText("Vegetable Cutlet");
            fooddesc.setText("Vegetable Cutlet, a delectable combination of mashed potato and green vegetables, is a crisp out side, soft inside Indian potato snack.");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*30;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*30;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*30;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }
        if (foodname.toLowerCase().equals("noodles")){
            waittag.setVisibility(View.INVISIBLE);
            food.setImageResource(R.drawable.quickfix6);
            rating.setText("4.2");
            saved.setText("25");
            order.setText("90");
            foodtype.setText("VEGETARIAN SNACK");
            foodName.setText("Noodles");
            fooddesc.setText("Veg Noodles are stir-fried noodles made with loads of mix vegetables and seasoned with taste maker spices");
            qty.setText("1");
            quantity= Integer.parseInt(qty.getText().toString());
            pricee = quantity*25;
            price.setText("₹"+String.valueOf(pricee));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    pricee = quantity*25;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity-=1;
                    if (quantity<0){quantity=0;}
                    pricee = quantity*25;
                    qty.setText(String.valueOf(quantity));
                    price.setText("₹"+String.valueOf(pricee));
                }
            });

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickcounter++;
                    if (clickcounter%2==0){
                        bookmark.setImageResource(R.drawable.ic_rectangle_344);
                        save=Integer.parseInt(saved.getText().toString());
                        save-=1;
                        saved.setText(String.valueOf(save));
                    } else {
                        bookmark.setImageResource(R.drawable.ic_rectangle_filled);
                        save=Integer.parseInt(saved.getText().toString());
                        save+=1;
                        saved.setText(String.valueOf(save));
                    }
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "CyberDiner");
                    intent.putExtra(Intent.EXTRA_TEXT, "From CyberDiner:\nDish: "+foodName.getText().toString()+"\nQuantity: "+qty.getText().toString()
                            +"\nPrice: "+price.getText().toString());
                    startActivity(Intent.createChooser(intent, "Share Via"));

                }
            });


        }

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(foodName.getText().toString()){
                    case "Vegetable Momos":  cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood1,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Chicken Nuggets": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood2,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Paneer Sandwich": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood3,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Popcorn": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood4,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Pizza Slice": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood5,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Black Coffee": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.popularfood6,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Samosa": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix1,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Chocolate Cookies": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix2,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "French Fries": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix3,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Chocobar Icecream": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix4,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Vegtable Cutlet": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix5,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                    case "Noodles": cartDB.insertIntoTheDatabase(foodName.getText().toString(),
                            R.drawable.quickfix6,
                            price.getText().toString(),
                            qty.getText().toString());
                        break;
                }
                i.putExtra("fimage",String.valueOf(food.getId()));
                i.putExtra("fname",foodName.getText().toString());
                i.putExtra("fqty",qty.getText().toString());
                i.putExtra("fprice",price.getText().toString());

                Toast.makeText(DetailsActivity.this,"Added to Cart",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });
    }

    private void createTableOnFirstStart() {
        cartDB.insertEmpty();

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}