package ru.geekbrains.android3_5.mvp.model.cache;

import java.util.List;

import io.reactivex.Single;
import ru.geekbrains.android3_5.mvp.model.entity.Repository;
import ru.geekbrains.android3_5.mvp.model.entity.User;

public interface ICache {
    void putUser(User user);
    Single<User> getUser(String username);

    void putUserRepos(User user, List<Repository> repositories);
    Single<List<Repository>> getUserRepos(User user);
}
