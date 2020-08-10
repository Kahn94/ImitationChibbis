package ru.kahn.imitationchibbis.application;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kahn.imitationchibbis.interfaces.ChibbisApi;

public class AppLoad extends Application {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://front-task.chibbistest.ru/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ChibbisApi chibbisApi = retrofit.create(ChibbisApi.class);

    private static AppLoad instanse;

    public static AppLoad getInstance() {
        if(instanse == null) {
            instanse = new AppLoad();
        }
        return instanse;
    }

    public ChibbisApi getApi() {
        return chibbisApi;
    }
}
