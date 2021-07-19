package com.example.foodorderapp;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    //getting json data from Asset folder
    static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}
