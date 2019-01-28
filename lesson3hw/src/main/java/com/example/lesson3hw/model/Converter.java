package com.example.lesson3hw.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import io.reactivex.Completable;

public class Converter implements iConverter {
    Context context;

    public Converter (Context context) {
        this.context = context;
    }

    @Override
    public Completable convertJpegToPng(String pathin, String pathout) {
        return Completable.fromAction(() -> {
            Thread.sleep(5000);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(pathin));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, context.getContentResolver().openOutputStream(Uri.parse(pathout)));
        });
    }
}
