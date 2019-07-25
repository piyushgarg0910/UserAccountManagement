package com.gargpiyush.android.useraccountmanagement.network;

import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.model.SignUpRequest;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 15:59.
 */
public interface AccountManagementResponse {

    @POST("auth/signup")
    Call<Void> postSignUpRequest(@Body SignUpRequest signUpRequest);

    @POST("auth/login")
    Call<LoginResponse> postLoginRequest(@Body LoginRequest loginRequest);

    @PATCH("user/me")
    Call<Void> patchUser(@Header("Authorization") String authHeader);

    @GET("user/me")
    Call<UserInfoResponse> getUserInfo(@Header("Authorization") String authHeader);
}
