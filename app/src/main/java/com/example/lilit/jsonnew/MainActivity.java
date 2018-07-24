package com.example.lilit.jsonnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText username_et, password_et;
    public static Retrofit RETROFIT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_et = (android.widget.EditText) findViewById(R.id.user);
        password_et = (android.widget.EditText)findViewById(R.id.pass);

    }

    /*public String serialize(Object object) {
        Gson gson = new Gson();
        //JsonObject jo = new JsonObject(object);
        JsonElement je = gson.toJsonTree(object);

        return je.toString();

    }*/


    /*public String serialize2(List<Object> objects, String arrKey, String objKey) {
        JsonArray ja = new JsonArray();
        for (Object object: objects) {
        Gson gson = new Gson();
        JsonElement je = gson.toJsonTree(objects);
            JsonObject jo = new JsonObject();
            jo.add(objKey, je);
            ja.add(jo);
        }

        JsonObject objMain = new JsonObject();
        objMain.add(arrKey,ja);

        return objMain.toString();
    }
*/

    public void login(View view) {

        String username = username_et.getText().toString();
        String password = password_et.getText().toString();
        User user = new User("valod", "valodik");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        //String json = serialize(user/*, "surveys","survey"*/);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
/*
        if(RETROFIT == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl("http://192.168.6.62:8080")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }*/

        OkHttpClient okHttpClient =new OkHttpClient();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("http://192.168.42.54:8080")
                .client(okHttpClient);

        Retrofit retrofit = retrofitBuilder.build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Void> call = apiService.setSurveys(body);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "uraaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Nikol" , "Nikol");
            }

        });

    }




/*
    public static Retrofit getClient(){
        if(RETROFIT == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return RETROFIT;
    }*/
}
