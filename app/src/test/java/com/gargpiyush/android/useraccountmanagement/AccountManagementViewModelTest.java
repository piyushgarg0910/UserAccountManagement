package com.gargpiyush.android.useraccountmanagement;

import com.gargpiyush.android.useraccountmanagement.model.DetailsUpdateRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.SignUpRequest;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponseData;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponseDataProfile;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Piyush Garg
 * on 7/25/2019
 * at 10:08.
 */
public class AccountManagementViewModelTest {

    @Mock
    private AccountManagementRepo accountManagementRepo;

    private AccountManagementViewModel accountManagementViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        accountManagementViewModel = Mockito.spy(new AccountManagementViewModel(accountManagementRepo));
    }

    @Test
    public void test_makeSignUpCall(){
        SignUpRequest signUpRequest = new SignUpRequest("ABC","ABC_12345",
                "ABC_12345","abc@xyz.com");
        accountManagementViewModel.makeSignUpCall(signUpRequest);
        Mockito.verify(accountManagementRepo,Mockito.times(1)).signUp(signUpRequest);
    }

    @Test
    public void test_makeLoginCall(){
        LoginRequest loginRequest = new LoginRequest("ABC_12345","abc@xyz.com");
        accountManagementViewModel.makeLoginCall(loginRequest);
        Mockito.verify(accountManagementRepo,Mockito.times(1)).login(loginRequest);
    }

    @Test
    public void test_makePatchDataCall(){Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2017-10-09");
        } catch (ParseException e){

        }
        DetailsUpdateRequest detailsUpdateRequest = new DetailsUpdateRequest("ABC",date);
        accountManagementViewModel.makePatchDataCall("token",detailsUpdateRequest);
        Mockito.verify(accountManagementRepo,Mockito.times(1))
                .patch("token",detailsUpdateRequest);
    }

    @Test
    public void test_makeGetInfoCall(){
        accountManagementViewModel.makeGetInfoCall("token");
        Mockito.verify(accountManagementRepo,Mockito.times(1)).getUserData("token");
    }
}
