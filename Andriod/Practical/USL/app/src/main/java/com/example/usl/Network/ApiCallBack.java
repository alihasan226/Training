package com.example.usl.Network;

import com.example.usl.Utils.Cv;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallBack<T> implements  Callback<T>{
        T t;
        public ApiCallBack(T t) {
            this.t=t;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if(response.code()==200){
                T body=response.body();
                EventBus.getDefault().post(body);
            }else{
                EventBus.getDefault().post(Cv.TIMEOUT);
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {

            EventBus.getDefault().post(Cv.TIMEOUT);
        }
}
