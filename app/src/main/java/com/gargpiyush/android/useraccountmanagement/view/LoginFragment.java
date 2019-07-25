package com.gargpiyush.android.useraccountmanagement.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gargpiyush.android.useraccountmanagement.R;
import com.gargpiyush.android.useraccountmanagement.model.LoginRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.model.UserInfoResponse;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 12:31.
 */

public class LoginFragment extends Fragment {

    @BindView(R.id.LoginPageButton)
    Button button;

    @BindView(R.id.LoginContainer)
    RelativeLayout layout;

    @BindView(R.id.EditTextEmailLogin)
    EditText emailEditText;

    @BindView(R.id.EditTextPasswordLogin)
    EditText passwordEditText;

    @BindView(R.id.AuthFailure)
    TextView textView;

    static boolean emailFilled = false;
    static boolean passwordFilled = false;

    private AccountManagementRepo accountManagementRepo;
    private AccountManagementViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        accountManagementRepo =(AccountManagementRepo) bundle.getSerializable("RepoObject");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText.addTextChangedListener(emailTextWatcher);
        passwordEditText.addTextChangedListener(passwordTextWatcher);
    }

    @OnClick(R.id.LoginPageButton)
    public void InflateDetailsFragment(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        LoginRequest loginRequest = new LoginRequest(password,email);
        AccountManagementViewModelFactory factory =
                new AccountManagementViewModelFactory(accountManagementRepo);
        viewModel = ViewModelProviders.of(this,factory)
                .get(AccountManagementViewModel.class);
        viewModel.makeLoginCall(loginRequest).observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(@Nullable LoginResponse loginResponse) {
                checkLoginResponse(loginResponse);
            }
        });
    }

    TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            emailFilled = !charSequence.toString().isEmpty();
            enableLoginButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            passwordFilled = !charSequence.toString().isEmpty();
            enableLoginButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void enableLoginButton(){
        button.setEnabled(emailFilled && passwordFilled);
        if (button.isEnabled()){
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_enabled,null));
        } else {
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_disabled,null));
        }
    }

    private void checkLoginResponse(LoginResponse loginResponse){
        if (loginResponse == null){
            textView.setVisibility(View.VISIBLE);
        } else {
            if (textView.getVisibility() == View.VISIBLE)
                textView.setVisibility(View.INVISIBLE);
            viewModel.makeGetInfoCall(loginResponse.getLoginResponseData()
                    .getaToken()).observe(this, new Observer<UserInfoResponse>() {
                @Override
                public void onChanged(@Nullable UserInfoResponse userInfoResponse) {
                    if (userInfoResponse.getData().getProfile().getLocation() == null
                            || userInfoResponse.getData().getProfile().getBirthday() == null){
                        inflateDetailsFragment(loginResponse);
                    }
                    else {
                        initiateDetailsActivity();
                    }
                }
            });
        }
    }

    private void inflateDetailsFragment(LoginResponse loginResponse){
        Fragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("RepoObject",accountManagementRepo);
        bundle.putSerializable("LoginResponse",loginResponse);
        fragment.setArguments(bundle);
        String fragmentName = "DetailsFragment";
        layout.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityLayout,fragment,fragmentName)
                .addToBackStack(fragmentName)
                .commit();
    }

    private void initiateDetailsActivity(){
        Log.e("going to","DetailsActivity");
    }
}
