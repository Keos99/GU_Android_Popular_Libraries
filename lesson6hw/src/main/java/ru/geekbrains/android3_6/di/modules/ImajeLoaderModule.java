package ru.geekbrains.android3_6.di.modules;

import android.widget.ImageView;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.mvp.model.cache.ImageCache;
import ru.geekbrains.android3_6.mvp.model.image.ImageLoader;
import ru.geekbrains.android3_6.mvp.model.image.android.ImageLoaderGlide;

@Module
public class ImajeLoaderModule {

    @Singleton
    @Provides
    public ImageLoader<ImageView> imageLoader(@Named("realm")ImageCache imageCache){
        return new ImageLoaderGlide(imageCache);
    }
}
