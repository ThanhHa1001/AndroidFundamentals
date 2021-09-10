package com.example.androidfundamentals.material.shapeappearancemodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

import java.security.MessageDigest;
import java.util.Objects;

/**
 * See more: https://github.com/material-components/material-components-android-examples/tree/develop/Owl
 *
 */
public class ShapeAppearanceTransformation implements Transformation<Bitmap> {
    private int shapeAppearanceId;
    private ShapeAppearanceModel shapeAppearanceModel = null;

    public ShapeAppearanceTransformation(@StyleRes int shapeAppearanceId) {
        this.shapeAppearanceId = shapeAppearanceId;
    }

    @NonNull
    @Override
    public Resource<Bitmap> transform(
            @NonNull Context context,
            @NonNull Resource<Bitmap> resource,
            int outWidth,
            int outHeight) {
        if (shapeAppearanceModel == null) {
            shapeAppearanceModel = ShapeAppearanceModel.builder(
                    context,
                    shapeAppearanceId,
                    0)
                    .build();
        }
        Bitmap bitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF rectF = new RectF(0f, 0f, outWidth, outHeight);
        Path path = new Path();
        ShapeAppearancePathProvider shapeAppearancePathProvider = new ShapeAppearancePathProvider();
        shapeAppearancePathProvider.calculatePath(shapeAppearanceModel, 1f, rectF, path);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(resource.get(), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawPath(path, paint);
        canvas.setBitmap(bitmap);

        return new BitmapResource(bitmap, Glide.get(context).getBitmapPool());
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(Objects.requireNonNull(getClass().getCanonicalName()).getBytes());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;
        ShapeAppearanceTransformation sATOther = (ShapeAppearanceTransformation) other;

        if (shapeAppearanceId != sATOther.shapeAppearanceId) return false;
        if (shapeAppearanceModel != sATOther.shapeAppearanceModel) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shapeAppearanceId;
        result = 31 * result + (shapeAppearanceModel != null ? shapeAppearanceModel.hashCode() : 0);
        return result;
    }
}
