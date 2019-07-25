package com.gargpiyush.android.useraccountmanagement.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gargpiyush.android.useraccountmanagement.R;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.util.AlertDialogScreen;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        AccountManagementRepo accountManagementRepo = new AccountManagementRepo(this);
        bundle.putSerializable("RepoObject",accountManagementRepo);
        toolbar.setNavigationIcon(R.drawable.ic_close_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
                while(backStackCount > 0){
                    getSupportFragmentManager().popBackStack();
                    backStackCount--;
                }
                InflateMainFragment(bundle);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        InflateMainFragment(bundle);
    }

    private void InflateMainFragment(Bundle bundle){
        Fragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.MainActivityLayout,fragment,"MainFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        new AlertDialogScreen().showAlert(this);
    }
}
