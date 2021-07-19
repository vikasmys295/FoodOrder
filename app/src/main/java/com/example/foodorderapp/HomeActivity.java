package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.adapters.HomeAdapter;
import com.example.foodorderapp.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Restaurant> restaurantList = new ArrayList<>();
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getting references and setting recyclerview adapter
        recyclerView = (RecyclerView) findViewById(R.id.restaurants);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(this,restaurantList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(homeAdapter);

        try {
            getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override//Adding searchview in the menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override//filtering the text in searchview
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                List<Restaurant> newList = new ArrayList<>();
                for(Restaurant restaurant : restaurantList){
                  if(restaurant.getRestaurant_name().toLowerCase().contains(userInput)||
                          restaurant.getRestaurant_dishes().toLowerCase().contains(userInput)||
                          restaurant.getRestaurant_cuisine_type().toLowerCase().contains(userInput)){
                      newList.add(restaurant);
                  }
                }
                homeAdapter.updateList(newList);
                return true;
            }
        });
        return true;
    }

    //getting data for recyclerview and json parsing
    private void getData() throws JSONException {
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "restaurants.json");
        JSONObject jsonObject = new JSONObject(jsonFileString);
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for(int i=0;i<jsonArray.length();i++){
         JSONObject restObj = jsonArray.getJSONObject(i);
         String restaurantNames = restObj.getString("name");
         String restaurantPhoto = restObj.getString("photograph");
         String restaurantAddress = restObj.getString("address");
         String restaurantRating = restObj.getString("rating");
         String restaurantAvgPrice = restObj.getString("avg_price");
         String restaurantDishesType = restObj.getString("dishes_type");
         String restaurantDishes = restObj.getString("dishes");
         String restaurantCuisine_type = restObj.getString("cuisine_type");
         Restaurant restaurantData = new Restaurant();
         restaurantData.setRestaurant_name(restaurantNames);
         restaurantData.setRestaurant_photo(restaurantPhoto);
         restaurantData.setRestaurant_address(restaurantAddress);
         restaurantData.setRestaurant_rating(restaurantRating);
         restaurantData.setRestaurant_avg_price(restaurantAvgPrice);
         restaurantData.setRestaurant_dishes_type(restaurantDishesType);
         restaurantData.setRestaurant_dishes(restaurantDishes);
         restaurantData.setRestaurant_cuisine_type(restaurantCuisine_type);
         restaurantList.add(restaurantData);
        }
    }
}
