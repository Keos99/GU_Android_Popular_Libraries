package ru.geekbrains.android3_4.ui.image;

import android.widget.ImageView;
import ru.geekbrains.android3_4.mvp.model.image.IImageLoader;

public class GlideImageLoader implements IImageLoader<ImageView> {

    @Override
    public void loadInto(String url, ImageView container) {
     /*   GlideApp.with(container.getContext())
                .asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(container);
     */
    }
}
