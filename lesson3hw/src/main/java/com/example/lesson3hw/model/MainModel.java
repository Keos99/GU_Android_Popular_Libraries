package com.example.lesson3hw.model;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.lesson3hw.App;
import com.example.lesson3hw.viev.MainActivity;

import java.io.File;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainModel {

    private String[] permissons = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public final static int REQUEST_ID = 100;
    private int TAKE_IMAGE_ID = 200;

    private MainActivity viev;
    private iConverter converter;
    private Scheduler scheduler;
    private Disposable convertationSubscription;

    public MainModel (MainActivity viev) {
        this.viev = viev;
        converter = new Converter(App.getInstance());
        scheduler = AndroidSchedulers.mainThread();
    }

    public void takeImage (){
        if (!checkPermissions()){
            requestPermissions();
        } else {
            onRequestPermissionsGranted();
        }
    }

    private boolean checkPermissions() {
        int readPermissionStatus = ContextCompat.checkSelfPermission(App.getInstance(), permissons[0]);
        int writePermissionStatus = ContextCompat.checkSelfPermission(App.getInstance(), permissons[1]);
        if (readPermissionStatus == PackageManager.PERMISSION_GRANTED && writePermissionStatus == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(viev.getActivity(), permissons, REQUEST_ID);
    }

    private void onRequestPermissionsGranted() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        viev.getActivity().startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_IMAGE_ID);
    }

    public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode) {
            case REQUEST_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onRequestPermissionsGranted();
                    return true;
                }
            }
        }
        return false;
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        if (requestCode == TAKE_IMAGE_ID) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    String outPath = App.getInstance().getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + "result.png";
                    path(imageUri.toString(), Uri.fromFile(new File(outPath)).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void path (String source, String dest){
        converter.convertJpegToPng(source, dest)
                .subscribeOn(Schedulers.computation())
                .observeOn(scheduler)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        convertationSubscription = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }


}
