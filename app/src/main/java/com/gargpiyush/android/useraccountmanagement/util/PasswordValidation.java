package com.gargpiyush.android.useraccountmanagement.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 12:55.
 */
public class PasswordValidation {

    public static boolean isPasswordValid(CharSequence charSequence)
    {
        String regEx = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^a*()_]).{8,16}";

        Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(charSequence);

        return (matcher.matches());
    }
}
