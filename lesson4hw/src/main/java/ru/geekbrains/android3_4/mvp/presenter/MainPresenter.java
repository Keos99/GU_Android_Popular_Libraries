package ru.geekbrains.android3_4.mvp.presenter;

import android.annotation.SuppressLint;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ru.geekbrains.android3_4.mvp.model.api.ApiHolder;
import ru.geekbrains.android3_4.mvp.model.entity.Repository;
import ru.geekbrains.android3_4.mvp.model.entity.User;
import ru.geekbrains.android3_4.mvp.model.repo.UsersRepo;
import ru.geekbrains.android3_4.mvp.presenter.list.IRepoListPresenter;
import ru.geekbrains.android3_4.mvp.view.MainView;
import ru.geekbrains.android3_4.mvp.view.item.RepoItemView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Scheduler mainThreadScheduler;
    private UsersRepo usersRepo;
    private User user;
    public RepoListPresenter repoListPresenter = new RepoListPresenter();

    public class RepoListPresenter implements IRepoListPresenter {
        PublishSubject<RepoItemView> clickSubject = PublishSubject.create();

        @Override
        public PublishSubject<RepoItemView> getClickSubject() {
            return clickSubject;
        }

        @Override
        public void bindView(RepoItemView view) {
            Repository repository = user.getRepos().get(view.getPos());
            view.setTitle(repository.getName());
        }

        @Override
        public int getRepoCount() {
            return user == null || user.getRepos() == null ? 0 : user.getRepos().size();
        }
    }

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.usersRepo = new UsersRepo();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadInfo();
    }

    @SuppressLint("CheckResult")
    public void loadInfo() {
        getViewState().showLoading();
        usersRepo.getUser("googlesamples")
                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                    this.user = user;
                    getViewState().showAvatar(user.getAvatarUrl());
                    getViewState().setUsername(user.getLogin());
                    usersRepo.getUsersRepos(user.getReposUrl())
                            .observeOn(mainThreadScheduler)
                            .subscribe(repositories -> {
                                this.user.setRepos(repositories);
                                getViewState().hideLoading();
                                getViewState().updateRepoList();
                            }, throwable -> {
                                Timber.e(throwable, "Failed to get user repos");
                                getViewState().showError(throwable.getMessage());
                            });


                }, throwable -> {
                    Timber.e(throwable, "Failed to get user");
                    getViewState().showError(throwable.getMessage());
                });
    }

}
