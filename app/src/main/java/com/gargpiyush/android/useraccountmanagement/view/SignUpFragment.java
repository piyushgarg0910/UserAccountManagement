package com.gargpiyush.android.useraccountmanagement.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gargpiyush.android.useraccountmanagement.R;
import com.gargpiyush.android.useraccountmanagement.model.SignUpRequest;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.util.EmailValidation;
import com.gargpiyush.android.useraccountmanagement.util.PasswordValidation;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 12:07.
 */

public class SignUpFragment extends Fragment {

    @BindView(R.id.SignUpPageButton)
    Button button;

    @BindView(R.id.signUpFragmentContainer)
    RelativeLayout layout;

    @BindView(R.id.EditTextName)
    EditText nameText;

    @BindView(R.id.EditTextLastName)
    EditText lNameText;

    @BindView(R.id.EditTextEmail)
    EditText emailText;

    @BindView(R.id.EditTextPassword)
    EditText passwordText;

    @BindView(R.id.TextViewEmailIncorrect)
    TextView emailTextView;

    @BindView(R.id.TextViewPasswordIncorrect)
    TextView passwordTextView;

    static boolean nameFilled = false;
    static boolean lNameFilled = false;
    static boolean emailFilled = false;
    static boolean passwordFilled = false;

    private AccountManagementRepo accountManagementRepo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        accountManagementRepo = (AccountManagementRepo) bundle.getSerializable("RepoObject");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameText.addTextChangedListener(nameTextWatcher);
        lNameText.addTextChangedListener(lNameTextWatcher);
        emailText.addTextChangedListener(emailTextWatcher);
        passwordText.addTextChangedListener(passwordTextWatcher);
    }

    @OnClick(R.id.SignUpPageButton)
    public void InflateLoginFragment(){
        if (emailText.getText() == null || !EmailValidation.isEmailValid(emailText.getText())){
            emailTextView.setVisibility(View.VISIBLE);
        } else if(passwordText.getText() == null || !PasswordValidation.isPasswordValid(passwordText.getText())){
            passwordTextView.setVisibility(View.VISIBLE);
        } else {
            String name = nameText.getText().toString() + " " + lNameText.getText().toString();
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            SignUpRequest signUpRequest = new SignUpRequest(name,password,password,email);
            AccountManagementViewModelFactory factory =
                    new AccountManagementViewModelFactory(accountManagementRepo);
            AccountManagementViewModel viewModel = ViewModelProviders.of(this,factory)
                    .get(AccountManagementViewModel.class);
            viewModel.makeSignUpCall(signUpRequest);
            Bundle bundle = new Bundle();
            bundle.putSerializable("RepoObject", accountManagementRepo);
            Fragment fragment = new LoginFragment();
            fragment.setArguments(bundle);
            String fragmentName = "LoginFragment";
            layout.setVisibility(View.GONE);
            FragmentTransaction fragmentTransaction = getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.MainActivityLayout, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }
    }

    TextWatcher nameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            nameFilled =!charSequence.toString().isEmpty();
            enableSignUpButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    TextWatcher lNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            lNameFilled =!charSequence.toString().isEmpty();
            enableSignUpButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            emailFilled =!charSequence.toString().isEmpty();
            enableSignUpButton();
            if (emailTextView.getVisibility() == View.VISIBLE){
                if (EmailValidation.isEmailValid(charSequence)){
                    emailTextView.setVisibility(View.INVISIBLE);
                }
            }
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
            passwordFilled =!charSequence.toString().isEmpty();
            enableSignUpButton();
            if (passwordTextView.getVisibility() == View.VISIBLE){
                if (PasswordValidation.isPasswordValid(charSequence)){
                    passwordTextView.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private void enableSignUpButton(){
        button.setEnabled((nameFilled && lNameFilled && emailFilled && passwordFilled));
        if (button.isEnabled()){
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_enabled,null));
        } else {
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_disabled,null));
        }
    }
}
