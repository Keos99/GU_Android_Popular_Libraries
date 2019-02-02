package com.example.lesson4httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar progressBar;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviev_http);
        progressBar = findViewById(R.id.progressbar_http);
        progressBar.setVisibility(View.GONE);
        editText = findViewById(R.id.edittext_http);
        button = findViewById(R.id.button_http);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Нажал!", Toast.LENGTH_SHORT).show();
                onClickHttp();
            }
        });
    }

    public void onClickHttp(){
        String bestUrl = "https://api.github.com/users";
        if (!(editText.getText().toString().isEmpty())){
            bestUrl += "/" + editText.getText();
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            new DownloadPageTask().execute(bestUrl);;
        } else {
            Toast.makeText(this, "Подключите интернет!",Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadPageTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return downloadOneUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
            progressBar.setVisibility(View.GONE);
            super.onPostExecute(s);
        }
    }

    private String downloadOneUrl (String adress) throws IOException{
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(adress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            //connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);

            int responcecode = connection.getResponseCode();

            if (responcecode == HttpURLConnection.HTTP_OK){

                System.out.println("Метод запроса: " + connection.getRequestMethod());
                System.out.println("Ответное сообщение: " + connection.getResponseMessage());

                Map<String, List<String>> myMap = connection.getHeaderFields();
                Set<String> myField = myMap.keySet();

                System.out.println("Далее следует заголовок:");

                for (String k : myField){
                    System.out.println("Ключ: " + k + " Значение " + myMap.get(k));
                }

                inputStream = connection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                int read = 0;

                while ((read = inputStream.read()) != -1){
                    byteArrayOutputStream.write(read);
                }

                byte[] result = byteArrayOutputStream.toByteArray();

                byteArrayOutputStream.close();
                data = new String(result);
            } else {
                data = connection.getResponseMessage() + " . Error Code : " + responcecode;
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                inputStream.close();
            }
        }
        return data;
    }
}
