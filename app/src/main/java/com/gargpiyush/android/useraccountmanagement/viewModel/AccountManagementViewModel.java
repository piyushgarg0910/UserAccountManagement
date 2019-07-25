package com.gargpiyush.android.useraccountmanagement.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Parcel;
import android.os.Parcelable;

import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.model.SignUpRequest;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;

import java.io.Serializable;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 16:08.
 */

public class AccountManagementViewModel extends ViewModel{

    private AccountManagementRepo accountManagementRepo;

    AccountManagementViewModel(AccountManagementRepo accountManagementRepo) {
        this.accountManagementRepo = accountManagementRepo;
    }

    public void makeSignUpCall(SignUpRequest signUpRequest){
        accountManagementRepo.signUp(signUpRequest);
    }

    public LiveData<LoginResponse> makeLoginCall(LoginRequest loginRequest){
        return accountManagementRepo.login(loginRequest);
    }

    public LiveData<UserInfoResponse> makeGetInfoCall(String token){
        return accountManagementRepo.getUserData(token);
    }

    public void makePatchDataCall(String token){
        accountManagementRepo.patch(token);
    }
}
