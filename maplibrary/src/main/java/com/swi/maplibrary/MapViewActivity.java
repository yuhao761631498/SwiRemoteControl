package com.swi.maplibrary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MapViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}