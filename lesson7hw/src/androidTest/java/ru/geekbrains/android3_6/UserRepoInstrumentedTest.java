package ru.geekbrains.android3_6;

import android.text.TextUtils;

import io.reactivex.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import ru.geekbrains.android3_6.di.DaggerTestComponent;
import ru.geekbrains.android3_6.di.TestComponent;
import ru.geekbrains.android3_6.di.modules.ApiModule;
import ru.geekbrains.android3_6.di.modules.CacheModule;
import ru.geekbrains.android3_6.mvp.model.cache.ICache;
import ru.geekbrains.android3_6.mvp.model.cache.RoomCache;
import ru.geekbrains.android3_6.mvp.model.entity.Repository;
import ru.geekbrains.android3_6.mvp.model.entity.User;
import ru.geekbrains.android3_6.mvp.model.repo.UsersRepo;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserRepoInstrumentedTest {

    @Inject
    UsersRepo usersRepo;

    private static MockWebServer mockWebServer;

    @BeforeClass
    public static void setupClass() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        mockWebServer.shutdown();
    }

    @Before
    public void setup(){
        TestComponent component = DaggerTestComponent
                .builder()
                .apiModule(new ApiModule(){
                    @Override
                    public String baseUrlProduction() {
                        return mockWebServer.url("/").toString();
                    }
                }).build();

        component.inject(this);
    }

    @Test
    public void getUser(){
        mockWebServer.enqueue(createUserResponse("someuser", "someavatar", "somerepos"));
        TestObserver<User> observer = new TestObserver<>();
        usersRepo.getUser("someuser").subscribe(observer);

        observer.awaitTerminalEvent();

        observer.assertValueCount(1);
        assertEquals(observer.values().get(0).getLogin(),"someuser");
        assertEquals(observer.values().get(0).getAvatarUrl(),"someavatar");
        assertEquals(observer.values().get(0).getReposUrl(),"somerepos");
    }

    @Test
    public void getUserRepos(){
        ArrayList<Repository> repos = new ArrayList<>();
        repos.add(new Repository("1", "repo1"));
        repos.add(new Repository("2", "repo2"));
        mockWebServer.enqueue(createUserReposResponse(repos));
        TestObserver<List<Repository>> observer = new TestObserver<>();
        usersRepo.getUserRepos(new User("login","url1","url2")).subscribe(observer);
        observer.awaitTerminalEvent();
        observer.assertValueCount(1);
        assertEquals(observer.values().get(0).get(0).getId(), "1");
        assertEquals(observer.values().get(0).get(0).getName(), "repo1");
        assertEquals(observer.values().get(0).get(1).getId(), "2");
        assertEquals(observer.values().get(0).get(1).getName(), "repo2");
    }

    private MockResponse createUserResponse(String login, String avatarUrl, String reposUrl){
        String body = "{\"login\":\"" + login + "\", \"avatar_url\":\"" + avatarUrl + "\", \"repos_url\":\"" + reposUrl + "\"}";
        return new MockResponse().setBody(body);
    }

    private MockResponse createUserReposResponse(List<Repository> repos){
        ArrayList<String> repoJsons = new ArrayList<>();
        for(Repository repository : repos){
            StringBuilder builder = new StringBuilder();
            builder.append("{\"id\":\"");
            builder.append(repository.getId());
            builder.append("\",\"name\":\"");
            builder.append(repository.getName());
            builder.append("\"}");
            repoJsons.add(builder.toString());
        }
        String body = "[" +   TextUtils.join(",", repoJsons) + "]";
        return new MockResponse().setBody(body);
    }
}
