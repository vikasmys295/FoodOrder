package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestaurantDetailActivity extends AppCompatActivity {

    TextView restaurant_name,restaurant_address,restaurant_rating,restaurant_avg_price,restaurant_dishes;
    ImageView restaurant_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        restaurant_name = (TextView)findViewById(R.id.restaurant_name);
        restaurant_photo = (ImageView)findViewById(R.id.restaurant_image);
        restaurant_address = (TextView)findViewById(R.id.restaurant_address);
        restaurant_rating = (TextView)findViewById(R.id.restaurant_rating);
        restaurant_avg_price = (TextView)findViewById(R.id.restaurant_price);
        restaurant_dishes = (TextView)findViewById(R.id.restaurant_dishes);

        Intent intent = getIntent();
        String name = intent.getStringExtra("rest_name");
        String photo = intent.getStringExtra("rest_photo");
        String rating = intent.getStringExtra("rest_rating");
        String address = intent.getStringExtra("rest_address");
        String price = intent.getStringExtra("rest_avg");
        String dishes = intent.getStringExtra("rest_dish");
        restaurant_name.setText(name);
        restaurant_rating.setText(rating);
        restaurant_address.setText(address);
        restaurant_dishes.setText(dishes);
        restaurant_avg_price.setText(price);
        Picasso.with(this).load(photo).into(restaurant_photo);

    }
}
