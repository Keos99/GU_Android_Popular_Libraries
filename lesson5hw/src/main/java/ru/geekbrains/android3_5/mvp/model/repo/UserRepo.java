package ru.geekbrains.android3_5.mvp.model.repo;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3_5.mvp.model.api.ApiHolder;
import ru.geekbrains.android3_5.mvp.model.cache.ICache;
import ru.geekbrains.android3_5.mvp.model.entity.Repository;
import ru.geekbrains.android3_5.mvp.model.entity.User;
import ru.geekbrains.android3_5.ui.NetworkStatus;

import java.util.List;

public class UserRepo {
    private ICache cache;

    public UserRepo(ICache cache) {
        this.cache = cache;
    }

    public Single<User> getUser(String username) {
        if (NetworkStatus.isOnline()){
            return ApiHolder.getInstance().getApi().getUser(username).subscribeOn(Schedulers.io())
                    .map(user -> {
                        cache.putUser(user);
                        return user;
                    });
        } else {
            return cache.getUser(username);
        }
    }

    public Single<List<Repository>> getUserRepos(User user) {
        if (NetworkStatus.isOnline()){
            return ApiHolder.getInstance().getApi().getUserRepos(user.getLogin()).subscribeOn(Schedulers.io())
                    .map(repositories -> {
                       cache.putUserRepos(user, repositories);
                       return repositories;
                    });
        } else {
            return cache.getUserRepos(user);
        }
    }
}
