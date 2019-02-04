package ru.geekbrains.android3_5.mvp.model.entity.realm;

public class CachedImage {
    String url;
    String path;

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
