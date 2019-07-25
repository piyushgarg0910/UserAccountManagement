package com.gargpiyush.android.useraccountmanagement.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 21:01.
 */
public class AccountManagementViewModelFactory implements ViewModelProvider.Factory {

    private AccountManagementRepo accountManagementRepo;

    public AccountManagementViewModelFactory(AccountManagementRepo accountManagementRepo) {
        this.accountManagementRepo = accountManagementRepo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AccountManagementViewModel(accountManagementRepo);
    }
}
