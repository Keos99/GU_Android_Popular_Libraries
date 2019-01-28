package com.example.lesson3hw.model;

import io.reactivex.Completable;

public interface iConverter {
    Completable convertJpegToPng(String source, String dest);
}
