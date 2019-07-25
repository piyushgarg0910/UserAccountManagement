package com.gargpiyush.android.useraccountmanagement.view;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.gargpiyush.android.useraccountmanagement.R;
import com.gargpiyush.android.useraccountmanagement.model.DetailsUpdateRequest;
import com.gargpiyush.android.useraccountmanagement.model.LoginResponse;
import com.gargpiyush.android.useraccountmanagement.repository.AccountManagementRepo;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModel;
import com.gargpiyush.android.useraccountmanagement.viewModel.AccountManagementViewModelFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 12:43.
 */
public class DetailsFragment extends Fragment {

    @BindView(R.id.DOBEditText)
    EditText dobEditText;

    @BindView(R.id.ContinueButton)
    Button button;

    @BindView(R.id.EditTextLocation)
    EditText locationEditText;

    @BindView(R.id.locationImage)
    ImageView image;

    final Calendar myCalendar = Calendar.getInstance();

    private LoginResponse loginResponse;
    private AccountManagementRepo accountManagementRepo;
    private AccountManagementViewModel viewModel;

    static boolean dobFilled = false;
    static boolean locationFilled = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        loginResponse = (LoginResponse) bundle.getSerializable("LoginResponse");
        accountManagementRepo = (AccountManagementRepo) bundle.getSerializable("RepoObject");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dobEditText.setOnClickListener(dobClickListener);
        dobEditText.addTextChangedListener(dobTextWatcher);
        locationEditText.addTextChangedListener(locationTextWatcher);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Need to be implemented
            }
        });
    }

    View.OnClickListener dobClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 0);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        }
    };

    TextWatcher dobTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            dobFilled = (!charSequence.toString().equals("MM/DD/YYYY"));
            enableContinueButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher locationTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            locationFilled = !charSequence.toString().isEmpty();
            enableContinueButton();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @OnClick(R.id.ContinueButton)
    public void continueToDetails(){
        String date = dobEditText.getText().toString();
        Log.e("Date",date);
        String location = locationEditText.getText().toString();
        Date date1 = null;
        try{
            date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.US).parse(date);
        }
        catch (ParseException e){
            Log.e("Parse Error", e.getMessage());
        }
        DetailsUpdateRequest detailsUpdateRequest = new DetailsUpdateRequest(location, date1);
        AccountManagementViewModelFactory factory =
                new AccountManagementViewModelFactory(accountManagementRepo);
        viewModel = ViewModelProviders.of(this,factory)
                .get(AccountManagementViewModel.class);
        viewModel.makePatchDataCall(loginResponse.getLoginResponseData().getaToken());
    }

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dobEditText.setText(sdf.format(myCalendar.getTime()));
    }

    private void enableContinueButton(){
        button.setEnabled(locationFilled && dobFilled);
        if (button.isEnabled()){
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_enabled,null));
        } else {
            button.setBackground(getResources()
                    .getDrawable(R.drawable.button_background_disabled,null));
        }
    }
}
