package ru.geekbrains.android3_6.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.mvp.model.cache.ICache;
import ru.geekbrains.android3_6.mvp.model.cache.ImageCache;
import ru.geekbrains.android3_6.mvp.model.cache.RoomCache;

@Module
public class CacheModule {

    @Named("room")
    @Provides
    public ICache cache(){
        return new RoomCache();
    }

    @Provides
    public ImageCache imageCache(){
        return new ImageCache();
    }
}
