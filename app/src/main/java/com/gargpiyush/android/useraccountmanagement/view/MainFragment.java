package com.gargpiyush.android.useraccountmanagement.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gargpiyush.android.useraccountmanagement.R;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 11:52.
 */

public class MainFragment extends Fragment {

    @BindView(R.id.LoginButton)
    Button LoginButton;

    @BindView(R.id.SignUpButton)
    Button SignUpButton;

    @BindView(R.id.mainFragmentLayout)
    LinearLayout layout;

    private AccountManagementRepo accountManagementRepo;

    private Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout,container,false);
        ButterKnife.bind(this,view);
        Bundle b = getArguments();
        accountManagementRepo = (AccountManagementRepo) b.getSerializable("RepoObject");
        bundle.putSerializable("RepoObject",accountManagementRepo);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick(R.id.LoginButton)
    public void InflateLoginFragment(){
        Fragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        String fragmentName = "LoginFragment";
        layout.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityLayout,fragment,fragmentName)
                .addToBackStack(fragmentName)
                .commit();
    }

    @OnClick(R.id.SignUpButton)
    public void InflateSignUpFragment(){
        Fragment fragment = new SignUpFragment();
        fragment.setArguments(bundle);
        String fragmentName = "SignUpFragment";
        layout.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityLayout,fragment,fragmentName)
                .addToBackStack(fragmentName)
                .commit();
    }
}
