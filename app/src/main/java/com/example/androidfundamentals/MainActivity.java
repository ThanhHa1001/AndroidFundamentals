package com.example.androidfundamentals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidfundamentals.material.shapeappearancemodel.MaterialShapeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.shapeAppearance).setOnClickListener(view -> {
            startActivity(new Intent(this, MaterialShapeActivity.class));
        });
    }
}