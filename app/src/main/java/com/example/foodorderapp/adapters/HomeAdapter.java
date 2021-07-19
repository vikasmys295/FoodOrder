package com.example.foodorderapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.RestaurantDetailActivity;
import com.example.foodorderapp.model.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    List<Restaurant> restaurantList;

    public HomeAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override//inflate the content_list
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override//binding the views
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.restaurantNames.setText(restaurant.getRestaurant_name());
        Picasso.with(context).load(restaurant.getRestaurant_photo()).resize(180,180)
                .into(holder.restaurantPhoto);

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    //updating the text after filterable
    public void updateList(List<Restaurant> newlist){
        restaurantList = new ArrayList<>();
        restaurantList.addAll(newlist);
        notifyDataSetChanged();
    }

    //inner class for MyviewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantNames;
        ImageView restaurantPhoto;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            restaurantNames = (TextView)itemView.findViewById(R.id.restname);
            restaurantPhoto = (ImageView)itemView.findViewById(R.id.imageView2);
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Restaurant restaurants = restaurantList.get(getLayoutPosition());
                    Intent intent = new Intent(context, RestaurantDetailActivity.class);
                    intent.putExtra("rest_name",restaurants.getRestaurant_name());
                    intent.putExtra("rest_photo",restaurants.getRestaurant_photo());
                    intent.putExtra("rest_address",restaurants.getRestaurant_address());
                    intent.putExtra("rest_rating",restaurants.getRestaurant_rating());
                    intent.putExtra("rest_avg",restaurants.getRestaurant_avg_price());
                    intent.putExtra("rest_dish",restaurants.getRestaurant_dishes_type());
                    context.startActivity(intent);
                }
            });
        }
    }
}
