package com.example.androidfundamentals.material.shapeappearancemodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.androidfundamentals.R;

public class MaterialShapeActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageViewTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_shape);

        imageView = findViewById(R.id.image);
        imageViewTwo = findViewById(R.id.imageTwo);

        ShapeAppearanceTransformation shapeAppearanceTransformation = new ShapeAppearanceTransformation(R.style.ShapeAppearance_SmallComponentType1);
        Glide.with(imageView)
                .load(R.drawable.image_cat)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(shapeAppearanceTransformation)
                .into(imageView);

        ShapeAppearanceTransformation shapeAppearanceTransformationNew = new ShapeAppearanceTransformation(R.style.ShapeAppearance_SmallComponentType2);
        Glide.with(imageViewTwo)
                .load(R.drawable.image_cat)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(shapeAppearanceTransformationNew)
                .into(imageViewTwo);
    }
}