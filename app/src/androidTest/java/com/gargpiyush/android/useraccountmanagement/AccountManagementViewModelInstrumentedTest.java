package com.gargpiyush.android.useraccountmanagement;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponseData;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponseData;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponseDataProfile;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 10:32.
 */

public class AccountManagementViewModelInstrumentedTest {

    private AccountManagementViewModel accountManagementViewModel;

    private LoginResponse response;
    private UserInfoResponse userInfoResponse;

    private MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<UserInfoResponse> userInfoResponseMutableLiveData = new MutableLiveData<>();

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        AccountManagementRepo accountManagementRepo = new AccountManagementRepo(context);
        accountManagementViewModel = new AccountManagementViewModel(accountManagementRepo);

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2017-10-09");
        } catch (ParseException e){

        }
        UserInfoResponseDataProfile profile = new UserInfoResponseDataProfile(date,"abc");
        UserInfoResponseData data = new UserInfoResponseData("ABC",profile);
        userInfoResponse = new UserInfoResponse(data,"ok");

        LoginResponseData d = new LoginResponseData("hjkadbhuiabsdiuga",
                "ajidshv7ty9.f3q7q6t2316.idsaiaghbdiusa");
        response = new LoginResponse("Msg",d);
    }

    @Test
    public void test_makeLoginCall_failure(){
        LoginRequest request = new LoginRequest("abc_123456","abc@xyz.com");
        accountManagementViewModel.makeLoginCall(request);
        assertNotEquals(response,loginResponseMutableLiveData.getValue());
    }

    @Test
    public void test_makeLoginCall_success(){
        LoginRequest request = new LoginRequest("abc_123456","abc@xyz.com");
        loginResponseMutableLiveData.postValue(response);
        accountManagementViewModel.makeLoginCall(request);
        assertEquals(response,loginResponseMutableLiveData.getValue());
    }

    @Test
    public void test_makeGetInfoCall_failure(){
        accountManagementViewModel.makeGetInfoCall("adsdsasdasd");
        assertNotEquals(userInfoResponse,userInfoResponseMutableLiveData.getValue());
    }

    @Test
    public void test_makeGetInfoCall_success(){
        userInfoResponseMutableLiveData.postValue(userInfoResponse);
        accountManagementViewModel.makeGetInfoCall("dsadadasdad");
        assertEquals(userInfoResponse,userInfoResponseMutableLiveData.getValue());
    }
}
