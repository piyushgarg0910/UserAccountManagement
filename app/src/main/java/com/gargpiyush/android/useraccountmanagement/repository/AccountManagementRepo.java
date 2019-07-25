package com.gargpiyush.android.useraccountmanagement.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.model.SignUpRequest;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;
import com.gargpiyush.android.useraccountmanagement.network.AccountManagementApi;
import com.gargpiyush.android.useraccountmanagement.network.AccountManagementResponse;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 14:50.
 */
public class AccountManagementRepo implements Serializable {

    private AccountManagementResponse accountManagementResponse;

    public AccountManagementRepo(Context context) {
        accountManagementResponse =
                AccountManagementApi.getRetrofitInstance(context)
                        .create(AccountManagementResponse.class);
    }


    public void signUp(SignUpRequest signUpRequest){
        accountManagementResponse.postSignUpRequest(signUpRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response){
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("SignUpRequestError", t.getMessage());
            }
        });
    }

    public LiveData<LoginResponse> login(LoginRequest loginRequest){
        final MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
        accountManagementResponse.postLoginRequest(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginRequestError", t.getMessage());
            }
        });
        return loginResponseMutableLiveData;
    }

    public LiveData<UserInfoResponse> getUserData(String token){
        String header = "Bearer " + token;
        final MutableLiveData<UserInfoResponse> userInfoResponseMutableLiveData = new MutableLiveData<>();
        accountManagementResponse.getUserInfo(header).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                userInfoResponseMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });
        return userInfoResponseMutableLiveData;
    }

    public void patch(String token){
        String header = "Bearer " + token;
        accountManagementResponse.patchUser(header).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
